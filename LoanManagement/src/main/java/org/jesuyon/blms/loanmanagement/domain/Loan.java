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
public class Loan extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private LoanApplication loanApplication;

    private BigDecimal interestRate;
    private BigDecimal totalRepaymentAmount;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    private String clerkId;
    private String customerId;
}

