package com.example.studymaterial.controller;

import com.example.studymaterial.model.Event;
import com.example.studymaterial.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * REST API Controller for event operations
 */
@RestController
@RequestMapping("/api/events")
public class ApiEventController {
    
    @Autowired
    private EventService eventService;
    
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            return ResponseEntity.ok(event.get());
        }
        return ResponseEntity.status(404).body("Event not found");
    }
    
    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        Event created = eventService.saveEvent(event);
        return ResponseEntity.ok(created);
    }
}
