package org.jesuyon.blms.usermanagement.user.domain.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String email;
    private String role;
    private boolean isActive;
}
