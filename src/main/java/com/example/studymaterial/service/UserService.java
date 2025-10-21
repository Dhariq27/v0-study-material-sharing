package com.example.studymaterial.service;

import com.example.studymaterial.model.User;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for User operations.
 */
public interface UserService {
    User registerUser(User user);
    Optional<User> authenticateUser(String username, String password);
    Optional<User> getUserById(Long userId);
    Optional<User> getUserByUsername(String username);
    List<User> getAllUsers();
    List<User> getUsersByRole(String role);
    void deleteUser(Long userId);
    void updateUser(User user);
}
