package com.example.studymaterial.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Announcement entity for system-wide announcements.
 */
@Entity
@Table(name = "ANNOUNCEMENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "announce_seq")
    @SequenceGenerator(name = "announce_seq", sequenceName = "ANNOUNCE_SEQ", allocationSize = 1)
    @Column(name = "ANNOUNCEMENT_ID")
    private Long announcementId;
    
    @Column(name = "TITLE", nullable = false, length = 200)
    private String title;
    
    @Column(name = "CONTENT", nullable = false, length = 2000)
    private String content;
    
    @Column(name = "POSTED_BY", nullable = false)
    private Long postedBy;
    
    @Column(name = "POST_DATE", nullable = false, updatable = false)
    private LocalDateTime postDate;
    
    @PrePersist
    protected void onCreate() {
        postDate = LocalDateTime.now();
    }
}
