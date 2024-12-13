package org.jesuyon.blms.usermanagement.user.service;

import lombok.RequiredArgsConstructor;
import org.jesuyon.blms.usermanagement.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
@RequiredArgsConstructor
public class User {

    @Autowired
    private org.jesuyon.blms.usermanagement.user.repository.User userRepository;

    public void createUser(org.jesuyon.blms.usermanagement.user.domain.User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new UserException("User with this email already exists", HttpStatus.CONFLICT);
        }
        org.jesuyon.blms.usermanagement.user.domain.User savedUser = org.jesuyon.blms.usermanagement.user.domain.User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dob(user.getDob())
                .email(user.getEmail())
                .isActive(true)
                .build();
        userRepository.save(savedUser);
    }
    public org.jesuyon.blms.usermanagement.user.domain.User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UserException("User not found", HttpStatus.NOT_FOUND));
    }

    public List<org.jesuyon.blms.usermanagement.user.domain.User> getAllUser() {
        List<org.jesuyon.blms.usermanagement.user.domain.User> userList = userRepository.findAll();
        if(userList.isEmpty()) {
            throw new UserException("No users found", HttpStatus.NOT_FOUND);
        }
        return userList;
    }
}
