package com.ramacciotti.registerapp.service.impl;

import com.ramacciotti.registerapp.model.User;
import com.ramacciotti.registerapp.repository.UserRepository;
import com.ramacciotti.registerapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User registerUser(String email, String rawPassword) {
        log.info("Trying to register user with email: {}", email);
        if (userRepository.findByEmail(email).isPresent()) {
            log.info("Registration failed - user with email {} already exists", email);
            throw new RuntimeException("User with this email already exists");
        }
        User user = new User();
        user.setEmail(email);
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        log.info("Password encrypted for email: {}", email);
        User savedUser = userRepository.save(user);
        log.info("User registered successfully with email: {}", email);
        return savedUser;
    }

    @Override
    public User loginUser(String email, String rawPassword) {
        log.info("Trying to login user with email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.info("Login failed - user with email {} not found", email);
                    return new RuntimeException("User not found");
                });

        boolean matches = passwordEncoder.matches(rawPassword, user.getPassword());
        if (matches) {
            log.info("Login successful for user with email: {}", email);
            return user;
        } else {
            log.info("Login failed - incorrect password for email: {}", email);
            throw new RuntimeException("Incorrect password");
        }
    }
}
