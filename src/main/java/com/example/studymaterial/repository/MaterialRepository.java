package com.example.studymaterial.repository;

import com.example.studymaterial.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Spring Data JPA Repository for Material entity.
 */
@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findBySubject(String subject);
    List<Material> findBySubjectAndSemester(String subject, String semester);
    List<Material> findByUploadedBy(Long userId);
}
