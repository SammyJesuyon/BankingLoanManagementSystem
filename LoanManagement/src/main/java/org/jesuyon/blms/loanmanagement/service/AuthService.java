package org.jesuyon.blms.loanmanagement.service;

import org.jesuyon.blms.loanmanagement.domain.User;
import org.jesuyon.blms.loanmanagement.dto.LoginRequestDto;
import org.jesuyon.blms.loanmanagement.domain.response.JWTAuthResponse;
import org.jesuyon.blms.loanmanagement.config.security.JwtTokenProvider;
import org.jesuyon.blms.loanmanagement.dto.UserDto;
import org.jesuyon.blms.loanmanagement.exception.LoanException;
import org.jesuyon.blms.loanmanagement.userDetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public JWTAuthResponse login(LoginRequestDto loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            String token = jwtTokenProvider.generateToken(authentication);
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            UserDto userDto = mapToUserDto(userDetails.getUser());

            return JWTAuthResponse.builder()
                    .accessToken(token)
                    .user(userDto)
                    .build();
        } catch (AuthenticationException e) {
            throw new LoanException("Invalid credentials", HttpStatus.BAD_REQUEST);
        }
    }

    private UserDto mapToUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
