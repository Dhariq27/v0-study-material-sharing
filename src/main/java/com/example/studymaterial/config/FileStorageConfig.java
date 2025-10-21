package com.example.studymaterial.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for file storage settings.
 */
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {
    
    private String uploadDir;
    
    public String getUploadDir() {
        return uploadDir;
    }
    
    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
