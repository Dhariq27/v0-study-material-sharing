<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Study Material System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="navbar">
        <div class="navbar-brand">Study Material System</div>
        <div class="navbar-menu">
            <a href="${pageContext.request.contextPath}/materials">Materials</a>
            <a href="${pageContext.request.contextPath}/events">Events</a>
            <span class="user-info">Welcome, ${username}</span>
            <a href="${pageContext.request.contextPath}/auth/logout" class="btn-logout">Logout</a>
        </div>
    </div>
    
    <div class="container">
        <h1>Dashboard</h1>
        
        <div class="dashboard-section">
            <h2>Recent Announcements</h2>
            <div class="announcements-list">
                <c:forEach var="announcement" items="${announcements}">
                    <div class="announcement-item">
                        <h3>${announcement.title}</h3>
                        <p>${announcement.content}</p>
                        <small>${announcement.postDate}</small>
                    </div>
                </c:forEach>
            </div>
        </div>
        
        <div class="dashboard-section">
            <h2>Recent Materials</h2>
            <div class="materials-list">
                <c:forEach var="material" items="${recentMaterials}">
                    <div class="material-item">
                        <h3>${material.title}</h3>
                        <p>Subject: ${material.subject} | Semester: ${material.semester}</p>
                        <p>Downloads: ${material.downloadCount}</p>
                        <a href="${pageContext.request.contextPath}/materials/${material.materialId}/download" class="btn-secondary">Download</a>
                    </div>
                </c:forEach>
            </div>
        </div>
        
        <div class="dashboard-section">
            <h2>Upcoming Events</h2>
            <div class="events-list">
                <c:forEach var="event" items="${events}">
                    <div class="event-item">
                        <h3>${event.eventName}</h3>
                        <p>Date: ${event.eventDate} | Location: ${event.location}</p>
                        <a href="${pageContext.request.contextPath}/events/${event.eventId}" class="btn-secondary">View Details</a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>
