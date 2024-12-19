package org.jesuyon.blms.loanmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanRepaymentCreationDto {
    private String loanId;
    private Double amount;
}
