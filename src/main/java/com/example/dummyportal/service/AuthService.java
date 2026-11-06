package com.example.dummyportal.service;

import com.example.dummyportal.dto.LoginRequest;
import com.example.dummyportal.dto.SignupRequest;
import com.example.dummyportal.entity.Branch;
import com.example.dummyportal.entity.User;
import com.example.dummyportal.repository.BranchRepository;
import com.example.dummyportal.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       BranchRepository branchRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(SignupRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        User user = new User();
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        if (req.getBranchId() != null) {
            Branch branch = branchRepository.findById(req.getBranchId())
                    .orElseThrow(() -> new IllegalArgumentException("Branch not found"));
            user.setBranch(branch);
        }
        return userRepository.save(user);
    }

    public User login(LoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return user;
    }
}
