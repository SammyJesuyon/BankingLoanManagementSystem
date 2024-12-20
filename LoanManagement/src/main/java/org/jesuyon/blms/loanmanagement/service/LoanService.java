package org.jesuyon.blms.loanmanagement.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.jesuyon.blms.loanmanagement.config.jms.Producer;
import org.jesuyon.blms.loanmanagement.domain.*;
import org.jesuyon.blms.loanmanagement.dto.*;
import org.jesuyon.blms.loanmanagement.exception.LoanApplicationNotFoundException;
import org.jesuyon.blms.loanmanagement.exception.LoanNotFoundException;
import org.jesuyon.blms.loanmanagement.exception.UserNotFoundException;
import org.jesuyon.blms.loanmanagement.repository.LoanApplicationRepository;
import org.jesuyon.blms.loanmanagement.repository.LoanRepository;
import org.jesuyon.blms.loanmanagement.repository.LoanRepaymentRepository;
import org.jesuyon.blms.loanmanagement.repository.UserRepository;
import org.jesuyon.blms.loanmanagement.specification.LoanSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.jesuyon.blms.loanmanagement.util.MapDtos.mapToDto;

@Service
public class LoanService {

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanRepaymentRepository loanRepaymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Producer producer;

    @PersistenceContext
    private EntityManager entityManager;

    public LoanApplicationDto applyForLoan(LoanApplicationCreationDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        boolean applicationExists = loanApplicationRepository.existsByUserIdAndStatus(dto.getUserId(), ApplicationStatus.PENDING);
        if (applicationExists) {
            throw new IllegalStateException("User already has a pending loan application.");
        }
        LoanApplication application = new LoanApplication();
        application.setUser(user);
        application.setRequestedAmount(dto.getRequestedAmount());
        application.setStatus(ApplicationStatus.PENDING);
        LoanApplication savedApplication = loanApplicationRepository.save(application);
        return LoanApplicationDto.builder()
                .id(savedApplication.getId())
                .user(mapToDto(savedApplication.getUser()))
                .requestedAmount(savedApplication.getRequestedAmount())
                .status(savedApplication.getStatus())
                .build();
    }

    public List<LoanApplicationDto> getAllApplications() {
        return loanApplicationRepository.findAll().stream()
                .map(application -> LoanApplicationDto.builder()
                        .id(application.getId())
                        .user(mapToDto(application.getUser()))
                        .requestedAmount(application.getRequestedAmount())
                        .status(application.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    public List<LoanApplicationDto> getPendingApplications() {
        return loanApplicationRepository.findByStatus(ApplicationStatus.PENDING).stream()
                .map(application -> LoanApplicationDto.builder()
                        .id(application.getId())
                        .user(mapToDto(application.getUser()))
                        .requestedAmount(application.getRequestedAmount())
                        .status(application.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public LoanDto approveLoanApplication(ApproveLoanDto approveLoanDto) {
        try{
            LoanApplication application = loanApplicationRepository.findById(approveLoanDto.getApplicationId())
                    .orElseThrow(() -> new LoanApplicationNotFoundException("Application not found"));

            if (!application.getStatus().equals(ApplicationStatus.PENDING)) {
                throw new IllegalStateException("Loan application is not pending");
            }

            application.setStatus(ApplicationStatus.APPROVED);
            loanApplicationRepository.save(application);

            User clerk = userRepository.findById(approveLoanDto.getClerkId()).orElseThrow(() -> new UserNotFoundException("Clerk not found"));

            Loan loan = new Loan();
            loan.setLoanApplication(application);
            loan.setClerk(clerk);
            loan.setInterestRate(approveLoanDto.getInterestRate());
            loan.setTotalRepaymentAmount(calculateTotalRepayment(application.getRequestedAmount(), approveLoanDto.getInterestRate()));
            loan.setStatus(LoanStatus.UNPAID);

            Loan savedLoan = loanRepository.save(loan);
            producer.sendNotification("Approved Loan " + savedLoan);
            return LoanDto.builder()
                    .id(savedLoan.getId())
                    .loanApplication(mapToDto(savedLoan.getLoanApplication()))
                    .clerk(mapToDto(savedLoan.getClerk()))
                    .interestRate(savedLoan.getInterestRate())
                    .totalRepaymentAmount(savedLoan.getTotalRepaymentAmount())
                    .status(savedLoan.getStatus())
                    .build();
        } catch (OptimisticLockException e) {
            throw new IllegalStateException("The application is being read/updated, please try again later.", e);
        }
    }

    @Transactional
    public LoanDto rejectLoanApplication(RejectLoanDto rejectLoanDto) {
        try{
            LoanApplication application = loanApplicationRepository.findById(rejectLoanDto.getApplicationId())
                    .orElseThrow(() -> new LoanApplicationNotFoundException("Application not found"));

            if (!application.getStatus().equals(ApplicationStatus.PENDING)) {
                throw new IllegalStateException("Loan application is not pending");
            }

            application.setStatus(ApplicationStatus.DENIED);
            loanApplicationRepository.save(application);

            User clerk = userRepository.findById(rejectLoanDto.getClerkId()).orElseThrow(() -> new UserNotFoundException("Clerk not found"));

            Loan loan = new Loan();
            loan.setLoanApplication(application);
            loan.setClerk(clerk);
            loan.setStatus(LoanStatus.DENIED);
            Loan savedLoan = loanRepository.save(loan);

            return LoanDto.builder()
                    .id(savedLoan.getId())
                    .loanApplication(mapToDto(savedLoan.getLoanApplication()))
                    .status(savedLoan.getStatus())
                    .build();
        } catch (OptimisticLockException e) {
            throw new IllegalStateException("The application is being read/updated, please try again later.", e);
        }
    }

    private BigDecimal calculateTotalRepayment(Double requestedAmount, BigDecimal interestRate) {
        return BigDecimal.valueOf(requestedAmount).add(BigDecimal.valueOf(requestedAmount).multiply(interestRate));
    }

    public LoanRepaymentDto makeRepayment(LoanRepaymentCreationDto dto) {
        Loan loan = loanRepository.findById(dto.getLoanId())
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));

        LoanRepayment repayment = new LoanRepayment();
        repayment.setLoan(loan);
        repayment.setAmount(dto.getAmount());

        loanRepaymentRepository.save(repayment);
        loan.setTotalRepaymentAmount(loan.getTotalRepaymentAmount().subtract(BigDecimal.valueOf(dto.getAmount())));
        Loan loanSaved = loanRepository.save(loan);
        return LoanRepaymentDto.builder()
                .id(repayment.getId())
                .loan(mapToDto(loanSaved))
                .amount(repayment.getAmount())
                .build();
    }

    public LoanDto getLoanById(String id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));
        return LoanDto.builder()
                .id(loan.getId())
                .loanApplication(mapToDto(loan.getLoanApplication()))
                .clerk(mapToDto(loan.getClerk()))
                .interestRate(loan.getInterestRate())
                .totalRepaymentAmount(loan.getTotalRepaymentAmount())
                .status(loan.getStatus())
                .build();
    }

    public LoanApplicationDto getApplicationById(String id) {
        LoanApplication application = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new LoanApplicationNotFoundException("Loan application not found"));
        return LoanApplicationDto.builder()
                .id(application.getId())
                .user(mapToDto(application.getUser()))
                .requestedAmount(application.getRequestedAmount())
                .status(application.getStatus())
                .build();
    }

    public List<LoanDto> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(loan -> LoanDto.builder()
                        .id(loan.getId())
                        .loanApplication(mapToDto(loan.getLoanApplication()))
                        .clerk(mapToDto(loan.getClerk()))
                        .interestRate(loan.getInterestRate())
                        .totalRepaymentAmount(loan.getTotalRepaymentAmount())
                        .status(loan.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    public List<LoanDto> getLoansByClerkAndStatus(ClerkAndStatusDto clerkAndStatusDto) {
        return loanRepository.findLoansByClerkAndStatus(clerkAndStatusDto.getClerkId(), clerkAndStatusDto.getStatus()).stream()
                .map(loan -> LoanDto.builder()
                        .id(loan.getId())
                        .loanApplication(mapToDto(loan.getLoanApplication()))
                        .clerk(mapToDto(loan.getClerk()))
                        .interestRate(loan.getInterestRate())
                        .totalRepaymentAmount(loan.getTotalRepaymentAmount())
                        .status(loan.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    public List<LoanDto> findLoansAboveInterestRate(BigDecimal interestRate) {
        return entityManager.createNamedQuery("Loan.findLoansAboveInterestRate", Loan.class)
                .setParameter("interestRate", interestRate)
                .getResultList().stream()
                .map(loan -> LoanDto.builder()
                        .id(loan.getId())
                        .loanApplication(mapToDto(loan.getLoanApplication()))
                        .clerk(mapToDto(loan.getClerk()))
                        .interestRate(loan.getInterestRate())
                        .totalRepaymentAmount(loan.getTotalRepaymentAmount())
                        .status(loan.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    public List<LoanDto> findAllWithFilter(LoanFilterDto loanFilterDto) {
        return loanRepository
                .findAll(LoanSpecification.filterLoansByApplicationAmountAndClerkId(
                        loanFilterDto.getMaxAmount(),
                        loanFilterDto.getMinAmount(),
                        loanFilterDto.getClerkId()))
                .stream()
                .map(loan -> LoanDto.builder()
                        .id(loan.getId())
                        .loanApplication(mapToDto(loan.getLoanApplication()))
                        .clerk(mapToDto(loan.getClerk()))
                        .interestRate(loan.getInterestRate())
                        .totalRepaymentAmount(loan.getTotalRepaymentAmount())
                        .status(loan.getStatus())
                        .build())
                .collect(Collectors.toList());
    }
}
