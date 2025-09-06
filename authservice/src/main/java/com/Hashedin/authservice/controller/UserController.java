package com.Hashedin.authservice.controller;

import com.Hashedin.authservice.dto.AuthResponse;
import com.Hashedin.authservice.dto.LoginRequest;
import com.Hashedin.authservice.dto.RegisterRequest;
import com.Hashedin.authservice.dto.RegistionResonse;
import com.Hashedin.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegistionResonse> registerUser(@RequestBody RegisterRequest registerRequest) {
        RegistionResonse registionResonse = userService.registionResonse(registerRequest);
        return new ResponseEntity(registionResonse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        AuthResponse login = userService.login(loginRequest);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

}
