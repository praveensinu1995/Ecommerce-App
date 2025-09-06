package com.Hashedin.authservice.service;

import com.Hashedin.authservice.dto.AuthResponse;
import com.Hashedin.authservice.dto.LoginRequest;
import com.Hashedin.authservice.dto.RegisterRequest;
import com.Hashedin.authservice.dto.RegistionResonse;
import com.Hashedin.authservice.entity.User;
import com.Hashedin.authservice.enums.Role;
import com.Hashedin.authservice.repository.UserRepo;
import com.Hashedin.authservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public RegistionResonse registionResonse(RegisterRequest registerRequest) {
        if (userRepo.existsByName(registerRequest.getUserName())) {
            throw new RuntimeException("userName already present");
        }
        if (userRepo.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email id already present");
        }

        User user = User.builder().
                userName(registerRequest.getUserName())
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ROLE_USER).build();
        userRepo.save(user);
        return new RegistionResonse("registered successfully");
    }

    public AuthResponse login(LoginRequest loginRequest) {
        User byUserName = userRepo.findByUserName(loginRequest.getUserName())
                .orElseThrow(() -> new RuntimeException("user not exist"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), byUserName.getPassword())) {
            throw new RuntimeException("password dose not match");
        }
        return new AuthResponse(jwtUtil.generateToken(byUserName));
    }

}
