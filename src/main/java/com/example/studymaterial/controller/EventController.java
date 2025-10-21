package com.example.studymaterial.controller;

import com.example.studymaterial.model.Event;
import com.example.studymaterial.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Controller for event operations (create, list, join).
 */
@Controller
@RequestMapping("/events")
public class EventController {
    
    @Autowired
    private EventService eventService;
    
    /**
     * Display all events
     */
    @GetMapping
    public String listEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "events";
    }
    
    /**
     * Display event details
     */
    @GetMapping("/{id}")
    public String viewEvent(@PathVariable Long id, Model model) {
        Optional<Event> event = eventService.getEventById(id);
        
        if (event.isPresent()) {
            long participantCount = eventService.getEventParticipantCount(id);
            model.addAttribute("event", event.get());
            model.addAttribute("participantCount", participantCount);
            return "event-detail";
        }
        
        return "redirect:/events";
    }
    
    /**
     * Display create event page (faculty/admin only)
     */
    @GetMapping("/create")
    public String showCreateEventPage(HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!"FACULTY".equals(role) && !"ADMIN".equals(role)) {
            return "redirect:/events";
        }
        
        return "create-event";
    }
    
    /**
     * Handle event creation
     */
    @PostMapping("/create")
    public String createEvent(@RequestParam String eventName,
                             @RequestParam String description,
                             @RequestParam String eventDate,
                             @RequestParam String location,
                             HttpSession session,
                             Model model) {
        String role = (String) session.getAttribute("role");
        if (!"FACULTY".equals(role) && !"ADMIN".equals(role)) {
            model.addAttribute("error", "Only faculty can create events");
            return "create-event";
        }
        
        Event event = new Event();
        event.setEventName(eventName);
        event.setDescription(description);
        event.setEventDate(LocalDateTime.parse(eventDate));
        event.setLocation(location);
        event.setCreatedBy((Long) session.getAttribute("userId"));
        
        eventService.createEvent(event);
        
        return "redirect:/events";
    }
    
    /**
     * Handle student joining an event
     */
    @PostMapping("/{id}/join")
    public String joinEvent(@PathVariable Long id, HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        
        if (userId == null) {
            return "redirect:/auth/login";
        }
        
        eventService.registerUserForEvent(id, userId);
        
        return "redirect:/events/" + id;
    }
}
