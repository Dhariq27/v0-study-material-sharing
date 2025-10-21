package com.example.studymaterial.service;

import com.example.studymaterial.model.Event;
import com.example.studymaterial.model.EventRegistration;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for Event operations.
 */
public interface EventService {
    Event createEvent(Event event);
    Optional<Event> getEventById(Long eventId);
    List<Event> getAllEvents();
    List<Event> getEventsByCreatedBy(Long userId);
    void deleteEvent(Long eventId);
    void updateEvent(Event event);
    EventRegistration registerUserForEvent(Long eventId, Long userId);
    List<EventRegistration> getEventParticipants(Long eventId);
    long getEventParticipantCount(Long eventId);
}
