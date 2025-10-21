package com.example.studymaterial.repository;

import com.example.studymaterial.model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA Repository for EventRegistration entity.
 */
@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
    List<EventRegistration> findByEventId(Long eventId);
    List<EventRegistration> findByUserId(Long userId);
    Optional<EventRegistration> findByEventIdAndUserId(Long eventId, Long userId);
}
