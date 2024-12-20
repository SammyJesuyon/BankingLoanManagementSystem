package org.jesuyon.blms.loanmanagement.util;

import org.jesuyon.blms.loanmanagement.domain.Loan;
import org.jesuyon.blms.loanmanagement.domain.LoanApplication;
import org.jesuyon.blms.loanmanagement.domain.LoanRepayment;
import org.jesuyon.blms.loanmanagement.domain.User;
import org.jesuyon.blms.loanmanagement.dto.LoanApplicationDto;
import org.jesuyon.blms.loanmanagement.dto.LoanDto;
import org.jesuyon.blms.loanmanagement.dto.LoanRepaymentDto;
import org.jesuyon.blms.loanmanagement.dto.UserDto;

public class MapDtos {
    public static UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public static LoanApplicationDto mapToDto(LoanApplication application) {
        return LoanApplicationDto.builder()
                .id(application.getId())
                .user(mapToDto(application.getUser()))
                .requestedAmount(application.getRequestedAmount())
                .status(application.getStatus())
                .build();
    }

    public static LoanRepaymentDto mapToDto(LoanRepayment repayment) {
        return LoanRepaymentDto.builder()
                .id(repayment.getId())
                .loan(mapToDto(repayment.getLoan()))
                .amount(repayment.getAmount())
                .build();
    }

    public static LoanDto mapToDto(Loan loan) {
        return LoanDto.builder()
                .id(loan.getId())
                .loanApplication(mapToDto(loan.getLoanApplication()))
                .clerk(mapToDto(loan.getClerk()))
                .interestRate(loan.getInterestRate())
                .totalRepaymentAmount(loan.getTotalRepaymentAmount())
                .status(loan.getStatus())
                .build();
    }
}
