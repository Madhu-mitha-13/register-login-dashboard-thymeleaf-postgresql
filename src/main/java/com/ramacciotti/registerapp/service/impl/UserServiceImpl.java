package com.ramacciotti.registerapp.service.impl;

import com.ramacciotti.registerapp.model.User;
import com.ramacciotti.registerapp.repository.UserRepository;
import com.ramacciotti.registerapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("USER")) // se quiser, coloque roles aqui
        );
    }

}
