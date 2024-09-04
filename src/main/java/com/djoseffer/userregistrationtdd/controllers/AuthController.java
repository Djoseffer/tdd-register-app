package com.djoseffer.userregistrationtdd.controllers;

import com.djoseffer.userregistrationtdd.config.TokenService;
import com.djoseffer.userregistrationtdd.domain.Dtos.LoginDto;
import com.djoseffer.userregistrationtdd.domain.Dtos.LoginResponse;
import com.djoseffer.userregistrationtdd.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto){
        LoginResponse response= tokenService.authenticateUser(loginDto);
        return ResponseEntity.ok(response);
    }
}
