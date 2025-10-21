package com.example.studymaterial.controller;

import com.example.studymaterial.model.Announcement;
import com.example.studymaterial.model.Material;
import com.example.studymaterial.model.Event;
import com.example.studymaterial.repository.AnnouncementRepository;
import com.example.studymaterial.service.MaterialService;
import com.example.studymaterial.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * Controller for dashboard operations.
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    
    @Autowired
    private MaterialService materialService;
    
    @Autowired
    private EventService eventService;
    
    @Autowired
    private AnnouncementRepository announcementRepository;
    
    /**
     * Display dashboard with announcements, recent materials, and events
     */
    @GetMapping
    public String showDashboard(HttpSession session, Model model) {
        // Check if user is logged in
        if (session.getAttribute("userId") == null) {
            return "redirect:/auth/login";
        }
        
        // Get announcements (last 5)
        List<Announcement> announcements = announcementRepository.findAllByOrderByPostDateDesc();
        if (announcements.size() > 5) {
            announcements = announcements.subList(0, 5);
        }
        
        // Get recent materials (last 10)
        List<Material> recentMaterials = materialService.getAllMaterials();
        if (recentMaterials.size() > 10) {
            recentMaterials = recentMaterials.subList(0, 10);
        }
        
        // Get upcoming events
        List<Event> events = eventService.getAllEvents();
        
        model.addAttribute("announcements", announcements);
        model.addAttribute("recentMaterials", recentMaterials);
        model.addAttribute("events", events);
        model.addAttribute("username", session.getAttribute("username"));
        
        return "dashboard";
    }
}
