package org.jesuyon.blms.usermanagement.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository("UserRepository")
public interface User extends JpaRepository<org.jesuyon.blms.usermanagement.user.domain.User, String> {
    Optional<org.jesuyon.blms.usermanagement.user.domain.User> findByEmail(String email);
    Optional<org.jesuyon.blms.usermanagement.user.domain.User> findById(String id);
    boolean existsByEmail(String email);
}
