package com.example.studymaterial.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * EventRegistration entity representing student participation in events.
 */
@Entity
@Table(name = "EVENT_REGISTRATIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRegistration {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reg_seq")
    @SequenceGenerator(name = "reg_seq", sequenceName = "REG_SEQ", allocationSize = 1)
    @Column(name = "REGISTRATION_ID")
    private Long registrationId;
    
    @Column(name = "EVENT_ID", nullable = false)
    private Long eventId;
    
    @Column(name = "USER_ID", nullable = false)
    private Long userId;
    
    @Column(name = "REGISTERED_AT", nullable = false, updatable = false)
    private LocalDateTime registeredAt;
    
    @PrePersist
    protected void onCreate() {
        registeredAt = LocalDateTime.now();
    }
}
