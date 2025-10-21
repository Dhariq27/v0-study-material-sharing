package com.example.studymaterial.dao;

import com.example.studymaterial.model.Event;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * DAO implementation for Event entity using JPA EntityManager.
 */
@Repository
public class EventDAOImpl implements EventDAO {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Event save(Event event) {
        entityManager.persist(event);
        return event;
    }
    
    @Override
    public Optional<Event> findById(Long eventId) {
        return Optional.ofNullable(entityManager.find(Event.class, eventId));
    }
    
    @Override
    public List<Event> findAll() {
        Query query = entityManager.createQuery("SELECT e FROM Event e ORDER BY e.eventDate DESC");
        return query.getResultList();
    }
    
    @Override
    public List<Event> findByCreatedBy(Long userId) {
        Query query = entityManager.createQuery("SELECT e FROM Event e WHERE e.createdBy = :userId ORDER BY e.eventDate DESC");
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    @Override
    public void delete(Long eventId) {
        Event event = entityManager.find(Event.class, eventId);
        if (event != null) {
            entityManager.remove(event);
        }
    }
    
    @Override
    public void update(Event event) {
        entityManager.merge(event);
    }
}
