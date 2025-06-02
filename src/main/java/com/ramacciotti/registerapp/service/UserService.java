package com.ramacciotti.registerapp.service;

import com.ramacciotti.registerapp.model.User;

public interface UserService {
    User createUser(String email, String rawPassword);
    User loginUser(String email, String rawPassword);
    void updateUserEmail(String currentUsername, String newEmail);
    void updateUserPassword(String currentUsername, String newPassword);
    void deleteUserByUsername(String username);
}
