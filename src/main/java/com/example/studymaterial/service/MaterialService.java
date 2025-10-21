package com.example.studymaterial.service;

import com.example.studymaterial.model.Material;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for Material operations.
 */
public interface MaterialService {
    Material uploadMaterial(Material material);
    Optional<Material> getMaterialById(Long materialId);
    List<Material> getAllMaterials();
    List<Material> getMaterialsBySubject(String subject);
    List<Material> getMaterialsBySubjectAndSemester(String subject, String semester);
    List<Material> getMaterialsByUploadedBy(Long userId);
    void deleteMaterial(Long materialId);
    void updateMaterial(Material material);
    void incrementDownloadCount(Long materialId);
}
