package com.example.studymaterial.controller;

import com.example.studymaterial.model.Material;
import com.example.studymaterial.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

/**
 * Controller for material operations (upload, download, list).
 */
@Controller
@RequestMapping("/materials")
public class MaterialController {
    
    @Autowired
    private MaterialService materialService;
    
    @Value("${file.upload.dir}")
    private String uploadDir;
    
    /**
     * Display materials list with optional filtering
     */
    @GetMapping
    public String listMaterials(@RequestParam(required = false) String subject,
                               @RequestParam(required = false) String semester,
                               Model model) {
        List<Material> materials;
        
        if (subject != null && !subject.isEmpty() && semester != null && !semester.isEmpty()) {
            materials = materialService.getMaterialsBySubjectAndSemester(subject, semester);
        } else if (subject != null && !subject.isEmpty()) {
            materials = materialService.getMaterialsBySubject(subject);
        } else {
            materials = materialService.getAllMaterials();
        }
        
        model.addAttribute("materials", materials);
        model.addAttribute("selectedSubject", subject);
        model.addAttribute("selectedSemester", semester);
        
        return "materials";
    }
    
    /**
     * Display upload page (faculty only)
     */
    @GetMapping("/upload")
    public String showUploadPage(HttpSession session, Model model) {
        // Check if user is faculty or admin
        String role = (String) session.getAttribute("role");
        if (!"FACULTY".equals(role) && !"ADMIN".equals(role)) {
            return "redirect:/materials";
        }
        
        return "upload";
    }
    
    /**
     * Handle file upload
     */
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                  @RequestParam String title,
                                  @RequestParam String description,
                                  @RequestParam String subject,
                                  @RequestParam String semester,
                                  HttpSession session,
                                  Model model) {
        // Check if user is faculty or admin
        String role = (String) session.getAttribute("role");
        if (!"FACULTY".equals(role) && !"ADMIN".equals(role)) {
            model.addAttribute("error", "Only faculty can upload materials");
            return "upload";
        }
        
        if (file.isEmpty()) {
            model.addAttribute("error", "Please select a file");
            return "upload";
        }
        
        try {
            // Create directory structure: /uploads/YYYY/MM/
            YearMonth yearMonth = YearMonth.now();
            String dirPath = uploadDir + yearMonth.getYear() + "/" + String.format("%02d", yearMonth.getMonthValue()) + "/";
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            // Save file
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = dirPath + fileName;
            file.transferTo(new File(filePath));
            
            // Create material record
            Material material = new Material();
            material.setTitle(title);
            material.setDescription(description);
            material.setSubject(subject);
            material.setSemester(semester);
            material.setFileName(file.getOriginalFilename());
            material.setFilePath(filePath);
            material.setFileType(file.getContentType());
            material.setUploadedBy((Long) session.getAttribute("userId"));
            material.setDownloadCount(0);
            
            materialService.uploadMaterial(material);
            
            model.addAttribute("success", "File uploaded successfully");
            return "upload";
        } catch (IOException e) {
            model.addAttribute("error", "Error uploading file: " + e.getMessage());
            return "upload";
        }
    }
    
    /**
     * Download material file
     */
    @GetMapping("/{id}/download")
    public void downloadMaterial(@PathVariable Long id, HttpSession session) throws IOException {
        Optional<Material> material = materialService.getMaterialById(id);
        
        if (material.isPresent()) {
            materialService.incrementDownloadCount(id);
            
            Path filePath = Paths.get(material.get().getFilePath());
            if (Files.exists(filePath)) {
                // File download logic would be implemented here
                // This is a placeholder for actual file streaming
            }
        }
    }
}
