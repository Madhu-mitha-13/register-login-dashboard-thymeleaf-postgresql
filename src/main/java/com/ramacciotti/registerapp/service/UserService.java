package com.ramacciotti.registerapp.service;

import com.ramacciotti.registerapp.model.User;

public interface UserService {
    User registerUser(String email, String rawPassword);

    User loginUser(String email, String rawPassword);
}
