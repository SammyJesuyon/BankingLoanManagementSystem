package org.jesuyon.blms.loanmanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LoanCreationDto {
    private String loanApplicationId;
    private BigDecimal interestRate;
    private BigDecimal totalRepaymentAmount;
}
