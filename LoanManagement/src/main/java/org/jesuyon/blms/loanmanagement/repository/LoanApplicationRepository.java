package org.jesuyon.blms.loanmanagement.repository;

import org.jesuyon.blms.loanmanagement.domain.ApplicationStatus;
import org.jesuyon.blms.loanmanagement.domain.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT la FROM LoanApplication la WHERE la.id = ?1")
    Optional<LoanApplication> findById(String id);

    List<LoanApplication> findByStatus(ApplicationStatus status);

    boolean existsByUserIdAndStatus(String userId, ApplicationStatus status);
}
