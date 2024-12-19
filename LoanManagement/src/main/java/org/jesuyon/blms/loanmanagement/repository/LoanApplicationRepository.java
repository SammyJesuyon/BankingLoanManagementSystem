package org.jesuyon.blms.loanmanagement.repository;

import org.jesuyon.blms.loanmanagement.domain.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, String> {
    LoanApplication findByLoanApplicationId(String loanApplicationId);
    List<LoanApplication> findByCustomerId(String customerId);
    List<LoanApplication> findByApplicationStatus(String applicationStatus);
}
