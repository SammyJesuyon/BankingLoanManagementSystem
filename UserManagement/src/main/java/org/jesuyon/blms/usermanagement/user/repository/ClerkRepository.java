package org.jesuyon.blms.usermanagement.user.repository;

import org.jesuyon.blms.usermanagement.user.domain.Clerk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClerkRepository extends JpaRepository<Clerk, String> {
}
