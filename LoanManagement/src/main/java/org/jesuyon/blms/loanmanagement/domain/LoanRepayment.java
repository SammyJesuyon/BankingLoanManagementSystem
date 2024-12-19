package org.jesuyon.blms.loanmanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanRepayment extends BaseEntity{
    @ManyToOne()
    private Loan loan;

    private Double amount;
}
