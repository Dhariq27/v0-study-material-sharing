package com.example.studymaterial.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Event entity representing study sessions or events.
 */
@Entity
@Table(name = "EVENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    @SequenceGenerator(name = "event_seq", sequenceName = "EVENT_SEQ", allocationSize = 1)
    @Column(name = "EVENT_ID")
    private Long eventId;
    
    @Column(name = "EVENT_NAME", nullable = false, length = 200)
    private String eventName;
    
    @Column(name = "DESCRIPTION", length = 1000)
    private String description;
    
    @Column(name = "EVENT_DATE", nullable = false)
    private LocalDateTime eventDate;
    
    @Column(name = "LOCATION", length = 200)
    private String location;
    
    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;
    
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
