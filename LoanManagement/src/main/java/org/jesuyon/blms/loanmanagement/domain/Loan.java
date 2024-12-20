package org.jesuyon.blms.loanmanagement.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(
                name = "Loan.findLoansAboveInterestRate",
                query = "SELECT l FROM Loan l " +
                        "WHERE l.interestRate > :interestRate"
        )
})
public class Loan extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private LoanApplication loanApplication;

    private BigDecimal interestRate;
    private BigDecimal totalRepaymentAmount;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    @ManyToOne
    private User clerk;
}

