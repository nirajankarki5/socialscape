package com.nirajan.socialscape.socialscape.service.auth;

import com.nirajan.socialscape.socialscape.dao.UserRepository;
import com.nirajan.socialscape.socialscape.dto.LoginRequest;
import com.nirajan.socialscape.socialscape.dto.LoginResponse;
import com.nirajan.socialscape.socialscape.dto.SignupRequest;
import com.nirajan.socialscape.socialscape.entity.User;
import com.nirajan.socialscape.socialscape.security.JwtUtil;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User signup(SignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole().name());

        String token = jwtUtil.generateToken(claims);
        return new LoginResponse(token);
    }

}
