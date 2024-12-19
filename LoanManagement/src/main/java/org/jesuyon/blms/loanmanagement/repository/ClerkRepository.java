package org.jesuyon.blms.loanmanagement.repository;

import org.jesuyon.blms.loanmanagement.domain.Clerk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClerkRepository extends JpaRepository<Clerk, String> {
    Clerk findByEmail(String email);
}
