package com.ramacciotti.registerapp.controller;

import com.ramacciotti.registerapp.dto.AuthRequest;
import com.ramacciotti.registerapp.model.User;
import com.ramacciotti.registerapp.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserServiceImpl userServiceImpl;

    public AuthController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        log.info("Received register request for email: {}", request.getEmail());

        try {
            User user = userServiceImpl.registerUser(request.getEmail(), request.getPassword());
            log.info("User registered successfully: {}", user.getEmail());
            return ResponseEntity.ok("User registered successfully: " + user.getEmail());
        } catch (RuntimeException e) {
            log.info("User registration failed: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        log.info("Received login request for email: {}", request.getEmail());

        try {
            User user = userServiceImpl.loginUser(request.getEmail(), request.getPassword());
            log.info("User logged in successfully: {}", user.getEmail());
            return ResponseEntity.ok("Login successful: " + user.getEmail());
        } catch (RuntimeException e) {
            log.info("User login failed: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}