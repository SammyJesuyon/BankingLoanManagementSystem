//package org.jesuyon.blms.usermanagement;
//
//import org.jesuyon.blms.usermanagement.exception.UserException;
//import org.jesuyon.blms.usermanagement.user.domain.Role;
//import org.jesuyon.blms.usermanagement.user.domain.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//
//@Component
//public class UserManagementTestRunner implements CommandLineRunner {
//
//    @Autowired
//    private org.jesuyon.blms.usermanagement.user.repository.User userRepository;
//
//
//    @Override
//    public void run(String... args) throws UserException {
//        System.out.println("Starting CommandLineRunner...");
//
//        // Create sample users
//        User user1 = User.builder()
//                .firstName("John")
//                .lastName("Doe")
//                .dob(new Date())
//                .email("john.doe@example.com")
//                .password("password123")
//                .role(Role.USER)
//                .isActive(true)
//                .build();
//
//        User user2 = User.builder()
//                .firstName("Jane")
//                .lastName("Smith")
//                .dob(new Date())
//                .email("jane.smith@example.com")
//                .password("password456")
//                .role(Role.ADMIN)
//                .isActive(false)
//                .build();
//
//        // Save users to the database
//        userRepository.save(user1);
//        userRepository.save(user2);
//
//        System.out.println("CommandLineRunner execution completed.");
//    }
//}
