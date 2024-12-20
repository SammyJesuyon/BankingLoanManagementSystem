package org.jesuyon.blms.loanmanagement.controller;

import org.jesuyon.blms.loanmanagement.config.jms.Producer;
import org.jesuyon.blms.loanmanagement.domain.response.BaseResponse;
import org.jesuyon.blms.loanmanagement.domain.response.ResponseBuilder;
import org.jesuyon.blms.loanmanagement.dto.*;
import org.jesuyon.blms.loanmanagement.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/blms/loans")
public class Loancontroller {

    @Autowired
    private LoanService loanService;

    @Autowired
    private Producer  producer;

    @PostMapping("/apply")
    public ResponseEntity<BaseResponse<LoanApplicationDto>> applyForLoan(@RequestBody LoanApplicationCreationDto loanApplicationCreationDto) {
        try {
            LoanApplicationDto loanApplicationDto = loanService.applyForLoan(loanApplicationCreationDto);
            producer.sendNotification("Loan application sent for " + loanApplicationDto.toString());
            return ResponseBuilder.buildResponse("Loan application submitted successfully", loanApplicationDto, 200);
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(e.getMessage(), null, 400);
        }
    }

    @PostMapping("/approve")
    public ResponseEntity<BaseResponse<LoanDto>> approveLoan(@RequestBody ApproveLoanDto approveLoanDto) {
        try {
            LoanDto loanDto = loanService.approveLoanApplication(approveLoanDto);
            producer.sendNotification("Loan approved for " + loanDto.toString());
            return ResponseBuilder.buildResponse("Loan application approved", loanDto, 200);
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(e.getMessage(), null, 400);
        }
    }

    @PostMapping("/reject")
    public ResponseEntity<BaseResponse<LoanDto>> rejectLoan(@RequestBody RejectLoanDto rejectLoanDto) {
        try {
            LoanDto loanDto = loanService.rejectLoanApplication(rejectLoanDto);
            producer.sendNotification("Loan rejected for " + loanDto.toString());
            return ResponseBuilder.buildResponse("Loan application rejected", loanDto, 200);
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(e.getMessage(), null, 400);
        }
    }

    @PostMapping("/repay_loan")
    public ResponseEntity<BaseResponse<LoanRepaymentDto>> repayLoan(@RequestBody LoanRepaymentCreationDto dto) {
        try {
            LoanRepaymentDto repay = loanService.makeRepayment(dto);
            return ResponseBuilder.buildResponse("Payment made successfully", repay, 200);
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(e.getMessage(), null, 400);
        }
    }

    @GetMapping("/loan_applications")
    public ResponseEntity<BaseResponse<Collection<LoanApplicationDto>>> getLoanApplication() {
        try {
            List<LoanApplicationDto> loanApplicationDto = loanService.getAllApplications();
            return ResponseBuilder.buildResponse("Loan applications retrieved successfully", loanApplicationDto, 200);
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(e.getMessage(), null, 400);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<LoanDto>> getLoanById(@PathVariable String id) {
        try {
            LoanDto loanDto = loanService.getLoanById(id);
            return ResponseBuilder.buildResponse("Loan retrieved successfully", loanDto, 200);
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(e.getMessage(), null, 400);
        }
    }

    @GetMapping("/loan_applications/{id}")
    public ResponseEntity<BaseResponse<LoanApplicationDto>> getLoanApplicationById(@PathVariable String id) {
        try {
            LoanApplicationDto loanApplicationDto = loanService.getApplicationById(id);
            return ResponseBuilder.buildResponse("Loan application retrieved successfully", loanApplicationDto, 200);
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(e.getMessage(), null, 400);
        }
    }

    @GetMapping("/pending_applications")
    public ResponseEntity<BaseResponse<Collection<LoanApplicationDto>>> getPendingLoanApplications() {
        try {
            List<LoanApplicationDto> loanApplicationDto = loanService.getPendingApplications();
            return ResponseBuilder.buildResponse("Pending loan applications retrieved successfully", loanApplicationDto, 200);
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(e.getMessage(), null, 400);
        }
    }

    @GetMapping
    public ResponseEntity<BaseResponse<Collection<LoanDto>>> getAllLoans() {
        try {
            List<LoanDto> loanDto = loanService.getAllLoans();
            return ResponseBuilder.buildResponse("Loans retrieved successfully", loanDto, 200);
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(e.getMessage(), null, 400);
        }
    }

    @GetMapping("/byClerkAndStatus")
    public ResponseEntity<BaseResponse<Collection<LoanDto>>> getLoansByClerkAndStatus(@RequestBody ClerkAndStatusDto clerkAndStatusDto) {
        try {
            List<LoanDto> loanDto = loanService.getLoansByClerkAndStatus(clerkAndStatusDto);
            return ResponseBuilder.buildResponse("Loans retrieved successfully", loanDto, 200);
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(e.getMessage(), null, 400);
        }
    }

    @GetMapping("/aboveInterestRate/{interestRate}")
    public ResponseEntity<BaseResponse<Collection<LoanDto>>> getLoansAboveInterestRate(@PathVariable BigDecimal interestRate) {
        try {
            List<LoanDto> loanDto = loanService.findLoansAboveInterestRate(interestRate);
            return ResponseBuilder.buildResponse("Loans retrieved successfully", loanDto, 200);
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(e.getMessage(), null, 400);
        }
    }

    @GetMapping("/byClerkAndAmount")
    public ResponseEntity<BaseResponse<Collection<LoanDto>>> getAllLoansWithFilter(@RequestBody LoanFilterDto loanFilterDto) {
        try {
            List<LoanDto> loanDto = loanService.findAllWithFilter(loanFilterDto);
            return ResponseBuilder.buildResponse("Loans retrieved successfully", loanDto, 200);
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(e.getMessage(), null, 400);
        }
    }
}
