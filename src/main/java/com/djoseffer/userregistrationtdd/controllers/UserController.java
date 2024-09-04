package com.djoseffer.userregistrationtdd.controllers;

import com.djoseffer.userregistrationtdd.domain.Dtos.RequestUserDto;
import com.djoseffer.userregistrationtdd.domain.Dtos.ResponseAllUsersDto;
import com.djoseffer.userregistrationtdd.domain.Dtos.ResponseDto;
import com.djoseffer.userregistrationtdd.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<ResponseDto> createUser(@RequestBody RequestUserDto userDto) {
       ResponseDto responseDto = userService.createUser(userDto);
       return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<ResponseAllUsersDto>> getAllUsers() {
        List<ResponseAllUsersDto> response = userService.getAllUsers();
       return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAllUsersDto> getUserById(@PathVariable Long id) {
       ResponseAllUsersDto response = userService.getUserById(id);
       return ResponseEntity.ok(response);
    }
}
