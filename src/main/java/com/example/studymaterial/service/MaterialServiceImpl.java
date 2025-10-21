package com.example.studymaterial.service;

import com.example.studymaterial.model.Material;
import com.example.studymaterial.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for Material operations.
 */
@Service
@Transactional
public class MaterialServiceImpl implements MaterialService {
    
    @Autowired
    private MaterialRepository materialRepository;
    
    @Override
    public Material uploadMaterial(Material material) {
        return materialRepository.save(material);
    }
    
    @Override
    public Optional<Material> getMaterialById(Long materialId) {
        return materialRepository.findById(materialId);
    }
    
    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }
    
    @Override
    public List<Material> getMaterialsBySubject(String subject) {
        return materialRepository.findBySubject(subject);
    }
    
    @Override
    public List<Material> getMaterialsBySubjectAndSemester(String subject, String semester) {
        return materialRepository.findBySubjectAndSemester(subject, semester);
    }
    
    @Override
    public List<Material> getMaterialsByUploadedBy(Long userId) {
        return materialRepository.findByUploadedBy(userId);
    }
    
    @Override
    public void deleteMaterial(Long materialId) {
        materialRepository.deleteById(materialId);
    }
    
    @Override
    public void updateMaterial(Material material) {
        materialRepository.save(material);
    }
    
    @Override
    public void incrementDownloadCount(Long materialId) {
        Optional<Material> material = materialRepository.findById(materialId);
        if (material.isPresent()) {
            Material m = material.get();
            m.setDownloadCount(m.getDownloadCount() + 1);
            materialRepository.save(m);
        }
    }
}
