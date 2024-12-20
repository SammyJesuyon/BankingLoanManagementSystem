package org.jesuyon.blms.loanmanagement.util;

import org.jesuyon.blms.loanmanagement.domain.LoanApplication;
import org.jesuyon.blms.loanmanagement.domain.User;
import org.jesuyon.blms.loanmanagement.dto.LoanApplicationDto;
import org.jesuyon.blms.loanmanagement.dto.UserDto;

public class Util {
    public static UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public static LoanApplicationDto mapToDto(LoanApplication application) {
        return LoanApplicationDto.builder()
                .id(application.getId())
                .user(mapToDto(application.getUser()))
                .requestedAmount(application.getRequestedAmount())
                .status(application.getStatus())
                .build();
    }
}
