package com.nirajan.socialscape.socialscape.controller;

import com.nirajan.socialscape.socialscape.dto.LoginRequest;
import com.nirajan.socialscape.socialscape.dto.LoginResponse;
import com.nirajan.socialscape.socialscape.dto.SignupRequest;
import com.nirajan.socialscape.socialscape.entity.User;
import com.nirajan.socialscape.socialscape.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/signup")
    public User signup(@RequestBody SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
