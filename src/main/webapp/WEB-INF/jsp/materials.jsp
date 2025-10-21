<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Materials - Study Material System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="navbar">
        <div class="navbar-brand">Study Material System</div>
        <div class="navbar-menu">
            <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
            <a href="${pageContext.request.contextPath}/events">Events</a>
            <a href="${pageContext.request.contextPath}/materials/upload" class="btn-primary">Upload Material</a>
            <a href="${pageContext.request.contextPath}/auth/logout" class="btn-logout">Logout</a>
        </div>
    </div>
    
    <div class="container">
        <h1>Study Materials</h1>
        
        <div class="filter-section">
            <form method="GET" action="${pageContext.request.contextPath}/materials">
                <input type="text" name="subject" placeholder="Subject" value="${selectedSubject}">
                <input type="text" name="semester" placeholder="Semester" value="${selectedSemester}">
                <button type="submit" class="btn-primary">Filter</button>
            </form>
        </div>
        
        <div class="materials-list">
            <c:forEach var="material" items="${materials}">
                <div class="material-card">
                    <h3>${material.title}</h3>
                    <p>${material.description}</p>
                    <p><strong>Subject:</strong> ${material.subject} | <strong>Semester:</strong> ${material.semester}</p>
                    <p><strong>Downloads:</strong> ${material.downloadCount}</p>
                    <a href="${pageContext.request.contextPath}/materials/${material.materialId}/download" class="btn-primary">Download</a>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
