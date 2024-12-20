package org.jesuyon.blms.loanmanagement.repository;

import org.jesuyon.blms.loanmanagement.domain.Loan;
import org.jesuyon.blms.loanmanagement.domain.LoanRepayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepaymentRepository extends JpaRepository<LoanRepayment, String> {
    List<LoanRepayment> findByLoanId(Loan loanId);
}
