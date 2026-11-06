package com.example.dummyportal.controller;

import com.example.dummyportal.dto.LoginRequest;
import com.example.dummyportal.dto.SignupRequest;
import com.example.dummyportal.entity.User;
import com.example.dummyportal.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest req) {
        User u = authService.signup(req);
        return ResponseEntity.ok(Map.of("message", "Signup successful", "id", u.getId()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        User u = authService.login(req);
        return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "id", u.getId(),
                "fullName", u.getFullName(),
                "email", u.getEmail()));
    }
}
