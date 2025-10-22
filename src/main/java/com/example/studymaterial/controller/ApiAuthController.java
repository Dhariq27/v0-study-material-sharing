package com.example.studymaterial.controller;

import com.example.studymaterial.model.User;
import com.example.studymaterial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * REST API Controller for authentication operations
 */
@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        
        Optional<User> user = userService.authenticateUser(username, password);
        
        if (user.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("userId", user.get().getUserId());
            response.put("username", user.get().getUsername());
            response.put("fullName", user.get().getFullName());
            response.put("role", user.get().getRole());
            response.put("email", user.get().getEmail());
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Invalid username or password");
            return ResponseEntity.status(401).body(response);
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        try {
            String username = request.get("username");
            String email = request.get("email");
            String password = request.get("password");
            String fullName = request.get("fullName");
            String role = request.getOrDefault("role", "student");
            
            if (username == null || username.trim().isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "Username is required");
                return ResponseEntity.status(400).body(response);
            }
            
            if (email == null || email.trim().isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "Email is required");
                return ResponseEntity.status(400).body(response);
            }
            
            if (password == null || password.trim().isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "Password is required");
                return ResponseEntity.status(400).body(response);
            }
            
            if (fullName == null || fullName.trim().isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "Full name is required");
                return ResponseEntity.status(400).body(response);
            }
            
            User user = new User();
            user.setUsername(username.trim());
            user.setEmail(email.trim());
            user.setPassword(password);
            user.setFullName(fullName.trim());
            user.setRole(role.toLowerCase());
            
            User registeredUser = userService.registerUser(user);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "User registered successfully");
            response.put("userId", registeredUser.getUserId());
            response.put("username", registeredUser.getUsername());
            response.put("email", registeredUser.getEmail());
            return ResponseEntity.status(201).body(response);
        } catch (DataIntegrityViolationException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Username or email already exists");
            return ResponseEntity.status(409).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Registration failed: " + e.getMessage());
            System.err.println("[v0] Registration error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(response);
        }
    }
}
