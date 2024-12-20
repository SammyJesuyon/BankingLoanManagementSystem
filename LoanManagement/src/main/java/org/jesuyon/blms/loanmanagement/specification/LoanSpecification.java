package org.jesuyon.blms.loanmanagement.specification;


import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.jesuyon.blms.loanmanagement.domain.Loan;
import org.jesuyon.blms.loanmanagement.domain.LoanApplication;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class LoanSpecification {

    public static Specification<Loan> filterLoansByApplicationAmountAndClerkId(BigDecimal maxAmount, BigDecimal minAmount, String clerkId) {
        return (root, query, criteriaBuilder) -> {
            Join<Loan, LoanApplication> loanApplicationJoin = root.join("loanApplication");
            Predicate lowAmountPredicate = criteriaBuilder.greaterThanOrEqualTo(loanApplicationJoin.get("requestedAmount"), minAmount);
            Predicate topAmountPredicate = criteriaBuilder.lessThanOrEqualTo(loanApplicationJoin.get("requestedAmount"), maxAmount);
            Predicate clerkPredicate = criteriaBuilder.equal(root.get("clerk").get("id"), clerkId);
            return criteriaBuilder.and(topAmountPredicate, lowAmountPredicate, clerkPredicate);
        };
    }
}
