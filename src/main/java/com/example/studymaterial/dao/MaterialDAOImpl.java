package com.example.studymaterial.dao;

import com.example.studymaterial.model.Material;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * DAO implementation for Material entity using JPA EntityManager.
 */
@Repository
public class MaterialDAOImpl implements MaterialDAO {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Material save(Material material) {
        entityManager.persist(material);
        return material;
    }
    
    @Override
    public Optional<Material> findById(Long materialId) {
        return Optional.ofNullable(entityManager.find(Material.class, materialId));
    }
    
    @Override
    public List<Material> findAll() {
        Query query = entityManager.createQuery("SELECT m FROM Material m ORDER BY m.uploadDate DESC");
        return query.getResultList();
    }
    
    @Override
    public List<Material> findBySubject(String subject) {
        Query query = entityManager.createQuery("SELECT m FROM Material m WHERE m.subject = :subject ORDER BY m.uploadDate DESC");
        query.setParameter("subject", subject);
        return query.getResultList();
    }
    
    @Override
    public List<Material> findBySubjectAndSemester(String subject, String semester) {
        Query query = entityManager.createQuery("SELECT m FROM Material m WHERE m.subject = :subject AND m.semester = :semester ORDER BY m.uploadDate DESC");
        query.setParameter("subject", subject);
        query.setParameter("semester", semester);
        return query.getResultList();
    }
    
    @Override
    public List<Material> findByUploadedBy(Long userId) {
        Query query = entityManager.createQuery("SELECT m FROM Material m WHERE m.uploadedBy = :userId ORDER BY m.uploadDate DESC");
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    @Override
    public void delete(Long materialId) {
        Material material = entityManager.find(Material.class, materialId);
        if (material != null) {
            entityManager.remove(material);
        }
    }
    
    @Override
    public void update(Material material) {
        entityManager.merge(material);
    }
    
    @Override
    public void incrementDownloadCount(Long materialId) {
        Material material = entityManager.find(Material.class, materialId);
        if (material != null) {
            material.setDownloadCount(material.getDownloadCount() + 1);
            entityManager.merge(material);
        }
    }
}
