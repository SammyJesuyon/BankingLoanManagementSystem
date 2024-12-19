package org.jesuyon.blms.loanmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanRepaymentDto {
    private String id;
    private String loanId;
    private Double amount;
}
