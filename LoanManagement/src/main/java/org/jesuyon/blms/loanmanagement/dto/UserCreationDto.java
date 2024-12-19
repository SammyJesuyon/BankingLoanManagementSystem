package org.jesuyon.blms.loanmanagement.dto;

import lombok.Getter;
import lombok.Setter;
import org.jesuyon.blms.loanmanagement.domain.RoleType;

@Getter
@Setter
public class UserCreationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private RoleType role;
}
