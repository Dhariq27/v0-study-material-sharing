package com.example.studymaterial.controller;

import com.example.studymaterial.model.User;
import com.example.studymaterial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        String username = request.get("username");
        String email = request.get("email");
        String password = request.get("password");
        String fullName = request.get("fullName");
        String role = request.getOrDefault("role", "student");
        
        if (userService.getUserByUsername(username).isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Username already exists");
            return ResponseEntity.status(400).body(response);
        }
        
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setFullName(fullName);
        newUser.setRole(role);
        
        userService.registerUser(newUser);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Registration successful");
        return ResponseEntity.ok(response);
    }
}
