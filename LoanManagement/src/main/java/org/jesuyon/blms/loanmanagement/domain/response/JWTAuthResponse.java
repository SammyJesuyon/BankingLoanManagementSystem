package org.jesuyon.blms.loanmanagement.domain.response;

import lombok.Builder;
import lombok.Data;
import org.jesuyon.blms.loanmanagement.dto.UserDto;

@Data
@Builder
public class JWTAuthResponse {
    private String accessToken;
    private UserDto user;

    public JWTAuthResponse(String token, UserDto user) {
        this.accessToken = token;
        this.user = user;
    }
}
