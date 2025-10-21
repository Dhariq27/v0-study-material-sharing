package com.example.studymaterial.dao;

import com.example.studymaterial.model.Event;
import java.util.List;
import java.util.Optional;

/**
 * DAO interface for Event entity operations.
 */
public interface EventDAO {
    Event save(Event event);
    Optional<Event> findById(Long eventId);
    List<Event> findAll();
    List<Event> findByCreatedBy(Long userId);
    void delete(Long eventId);
    void update(Event event);
}
