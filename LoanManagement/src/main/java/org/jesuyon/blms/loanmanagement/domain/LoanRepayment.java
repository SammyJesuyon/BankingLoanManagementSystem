package org.jesuyon.blms.loanmanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class LoanRepayment extends BaseEntity{
    @ManyToOne()
    private Loan loan;

    private Double amount;
}
