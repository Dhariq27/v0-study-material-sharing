package com.example.studymaterial.controller;

import com.example.studymaterial.model.User;
import com.example.studymaterial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Controller for authentication operations (login, logout, registration).
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    /**
     * Display login page
     */
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    
    /**
     * Handle login form submission
     */
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, 
                             @RequestParam String password,
                             HttpSession session,
                             Model model) {
        Optional<User> user = userService.authenticateUser(username, password);
        
        if (user.isPresent()) {
            session.setAttribute("userId", user.get().getUserId());
            session.setAttribute("username", user.get().getUsername());
            session.setAttribute("role", user.get().getRole());
            session.setAttribute("fullName", user.get().getFullName());
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
    
    /**
     * Display registration page
     */
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
    
    /**
     * Handle registration form submission
     */
    @PostMapping("/register")
    public String handleRegister(@RequestParam String username,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String fullName,
                                @RequestParam String role,
                                Model model) {
        // Check if user already exists
        if (userService.getUserByUsername(username).isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setFullName(fullName);
        newUser.setRole(role);
        
        userService.registerUser(newUser);
        model.addAttribute("success", "Registration successful. Please login.");
        return "redirect:/auth/login";
    }
    
    /**
     * Handle logout
     */
    @GetMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
