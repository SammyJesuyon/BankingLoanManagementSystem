package org.jesuyon.blms.loanmanagement.controller;

import org.jesuyon.blms.loanmanagement.domain.response.BaseResponse;
import org.jesuyon.blms.loanmanagement.domain.response.ResponseBuilder;
import org.jesuyon.blms.loanmanagement.dto.LoginRequestDto;
import org.jesuyon.blms.loanmanagement.domain.response.JWTAuthResponse;
import org.jesuyon.blms.loanmanagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blms/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<BaseResponse<JWTAuthResponse>> login(@RequestBody LoginRequestDto loginRequest) {
        try {
            JWTAuthResponse jwtAuthResponse = authService.login(loginRequest);
            return ResponseBuilder.buildResponse("Login successful", jwtAuthResponse, HttpStatus.OK.value());
        } catch (Exception e) {
            return ResponseBuilder.buildResponse("Login failed", null, HttpStatus.BAD_REQUEST.value());
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
