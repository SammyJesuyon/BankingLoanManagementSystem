package org.jesuyon.blms.usermanagement.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
