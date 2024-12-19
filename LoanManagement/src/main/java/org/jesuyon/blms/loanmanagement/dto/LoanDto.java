package org.jesuyon.blms.loanmanagement.dto;

import lombok.Getter;
import lombok.Setter;
import org.jesuyon.blms.loanmanagement.domain.LoanStatus;

import java.math.BigDecimal;

@Getter
@Setter
public class LoanDto {
    private String id;
    private String loanApplicationId;
    private BigDecimal interestRate;
    private BigDecimal totalRepaymentAmount;
    private LoanStatus status;
    private String clerkId;
}
