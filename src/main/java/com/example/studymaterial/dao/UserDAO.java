package com.example.studymaterial.dao;

import com.example.studymaterial.model.User;
import java.util.List;
import java.util.Optional;

/**
 * DAO interface for User entity operations.
 */
public interface UserDAO {
    User save(User user);
    Optional<User> findById(Long userId);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    List<User> findByRole(String role);
    void delete(Long userId);
    void update(User user);
}
