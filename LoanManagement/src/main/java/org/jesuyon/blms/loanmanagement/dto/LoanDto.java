package org.jesuyon.blms.loanmanagement.dto;

import lombok.*;
import org.jesuyon.blms.loanmanagement.domain.LoanStatus;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanDto {
    private String id;
    private LoanApplicationDto loanApplication;
    private BigDecimal interestRate;
    private BigDecimal totalRepaymentAmount;
    private LoanStatus status;
    private UserDto clerk;
}
