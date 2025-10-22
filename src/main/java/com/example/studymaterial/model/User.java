package com.example.studymaterial.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * User entity representing a system user (student, faculty, or admin).
 * Roles: STUDENT, FACULTY, ADMIN
 */
@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long userId;
    
    @Column(name = "USERNAME", unique = true, nullable = false, length = 50)
    private String username;
    
    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;
    
    @Column(name = "EMAIL", unique = true, nullable = false, length = 100)
    private String email;
    
    @Column(name = "FULL_NAME", nullable = false, length = 100)
    private String fullName;
    
    @Column(name = "ROLE", nullable = false, length = 20)
    private String role; // STUDENT, FACULTY, ADMIN
    
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
