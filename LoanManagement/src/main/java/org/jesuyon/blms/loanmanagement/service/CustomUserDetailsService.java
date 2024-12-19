package org.jesuyon.blms.loanmanagement.service;

import org.jesuyon.blms.loanmanagement.domain.Clerk;
import org.jesuyon.blms.loanmanagement.domain.Customer;
import org.jesuyon.blms.loanmanagement.repository.ClerkRepository;
import org.jesuyon.blms.loanmanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClerkRepository clerkRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try finding Clerk
        Clerk clerk = clerkRepository.findByEmail(username);
        if (clerk != null) {
            return new org.springframework.security.core.userdetails.User(
                    clerk.getEmail(),
                    clerk.getPassword(),
                    AuthorityUtils.createAuthorityList("ROLE_CLERK")
            );
        }

        Customer customer = customerRepository.findByEmail(username);
        if (customer != null) {
            return new org.springframework.security.core.userdetails.User(
                    customer.getEmail(),
                    customer.getPassword(),
                    AuthorityUtils.createAuthorityList("ROLE_CUSTOMER")
            );
        }

        throw new UsernameNotFoundException("User not found with email: " + username);
    }
}

