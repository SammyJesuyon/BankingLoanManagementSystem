package org.jesuyon.blms.loanmanagement.controller;

import org.jesuyon.blms.loanmanagement.domain.response.BaseResponse;
import org.jesuyon.blms.loanmanagement.domain.response.ResponseBuilder;
import org.jesuyon.blms.loanmanagement.dto.UserCreationDto;
import org.jesuyon.blms.loanmanagement.dto.UserDto;
import org.jesuyon.blms.loanmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blms/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<BaseResponse<UserDto>> createUser(@RequestBody UserCreationDto userDto) {
        try{
            UserDto user = userService.createUser(userDto);
            return ResponseBuilder.buildResponse("User created successfully", user, 200);
        }
        catch (Exception e){
            return ResponseBuilder.buildResponse(e.getMessage(), null, 400);
        }
    }
}
