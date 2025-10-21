package com.example.studymaterial.dao;

import com.example.studymaterial.model.Material;
import java.util.List;
import java.util.Optional;

/**
 * DAO interface for Material entity operations.
 */
public interface MaterialDAO {
    Material save(Material material);
    Optional<Material> findById(Long materialId);
    List<Material> findAll();
    List<Material> findBySubject(String subject);
    List<Material> findBySubjectAndSemester(String subject, String semester);
    List<Material> findByUploadedBy(Long userId);
    void delete(Long materialId);
    void update(Material material);
    void incrementDownloadCount(Long materialId);
}
