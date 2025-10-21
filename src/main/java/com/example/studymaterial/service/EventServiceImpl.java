package com.example.studymaterial.service;

import com.example.studymaterial.model.Event;
import com.example.studymaterial.model.EventRegistration;
import com.example.studymaterial.repository.EventRepository;
import com.example.studymaterial.repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for Event operations.
 */
@Service
@Transactional
public class EventServiceImpl implements EventService {
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;
    
    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
    
    @Override
    public Optional<Event> getEventById(Long eventId) {
        return eventRepository.findById(eventId);
    }
    
    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    
    @Override
    public List<Event> getEventsByCreatedBy(Long userId) {
        return eventRepository.findByCreatedBy(userId);
    }
    
    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }
    
    @Override
    public void updateEvent(Event event) {
        eventRepository.save(event);
    }
    
    @Override
    public EventRegistration registerUserForEvent(Long eventId, Long userId) {
        // Check if already registered
        Optional<EventRegistration> existing = eventRegistrationRepository.findByEventIdAndUserId(eventId, userId);
        if (existing.isPresent()) {
            return existing.get();
        }
        
        EventRegistration registration = new EventRegistration();
        registration.setEventId(eventId);
        registration.setUserId(userId);
        return eventRegistrationRepository.save(registration);
    }
    
    @Override
    public List<EventRegistration> getEventParticipants(Long eventId) {
        return eventRegistrationRepository.findByEventId(eventId);
    }
    
    @Override
    public long getEventParticipantCount(Long eventId) {
        return eventRegistrationRepository.findByEventId(eventId).size();
    }
}
