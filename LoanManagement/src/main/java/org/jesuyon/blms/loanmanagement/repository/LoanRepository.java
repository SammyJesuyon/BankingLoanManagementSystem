package org.jesuyon.blms.loanmanagement.repository;

import org.jesuyon.blms.loanmanagement.domain.Loan;
import org.jesuyon.blms.loanmanagement.domain.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String>, JpaSpecificationExecutor<Loan> {
    List<Loan> findByStatus(String status);

    @Query("SELECT l FROM Loan l " +
            "JOIN l.loanApplication la " +
            "JOIN l.clerk c " +
            "WHERE c.id = :clerkId AND l.status = :status")
    List<Loan> findLoansByClerkAndStatus(String clerkId, LoanStatus status);
}
