package com.example.studymaterial.repository;

import com.example.studymaterial.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Spring Data JPA Repository for Event entity.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCreatedBy(Long userId);
}
