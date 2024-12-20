package org.jesuyon.blms.loanmanagement.dto;

import lombok.*;
import org.jesuyon.blms.loanmanagement.domain.Loan;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanRepaymentDto {
    private String id;
    private Loan loan;
    private Double amount;
}
