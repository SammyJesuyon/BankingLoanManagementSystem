package org.jesuyon.blms.loanmanagement.repository;

import org.jesuyon.blms.loanmanagement.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByEmail(String email);
}