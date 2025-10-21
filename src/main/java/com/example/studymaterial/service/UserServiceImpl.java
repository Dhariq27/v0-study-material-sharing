package com.example.studymaterial.service;

import com.example.studymaterial.model.User;
import com.example.studymaterial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for User operations.
 * Handles business logic for user management and authentication.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public User registerUser(User user) {
        // Encode password before saving (recommended: use BCrypt in production)
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    @Override
    public Optional<User> authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }
    
    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
    
    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }
    
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    
    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }
}
