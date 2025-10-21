package com.example.studymaterial.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Material entity representing study materials (PDFs, DOCs, PPTs).
 * Stores file path and metadata for uploaded materials.
 */
@Entity
@Table(name = "MATERIALS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_seq")
    @SequenceGenerator(name = "material_seq", sequenceName = "MATERIAL_SEQ", allocationSize = 1)
    @Column(name = "MATERIAL_ID")
    private Long materialId;
    
    @Column(name = "TITLE", nullable = false, length = 200)
    private String title;
    
    @Column(name = "DESCRIPTION", length = 1000)
    private String description;
    
    @Column(name = "SUBJECT", nullable = false, length = 100)
    private String subject;
    
    @Column(name = "SEMESTER", nullable = false, length = 20)
    private String semester;
    
    @Column(name = "FILE_PATH", nullable = false, length = 500)
    private String filePath;
    
    @Column(name = "FILE_NAME", nullable = false, length = 255)
    private String fileName;
    
    @Column(name = "FILE_TYPE", length = 50)
    private String fileType;
    
    @Column(name = "UPLOADED_BY", nullable = false)
    private Long uploadedBy;
    
    @Column(name = "UPLOAD_DATE", nullable = false, updatable = false)
    private LocalDateTime uploadDate;
    
    @Column(name = "DOWNLOAD_COUNT")
    private Integer downloadCount = 0;
    
    @PrePersist
    protected void onCreate() {
        uploadDate = LocalDateTime.now();
    }
}
