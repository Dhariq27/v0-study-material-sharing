<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Upload Material - Study Material System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="navbar">
        <div class="navbar-brand">Study Material System</div>
        <div class="navbar-menu">
            <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
            <a href="${pageContext.request.contextPath}/materials">Materials</a>
            <a href="${pageContext.request.contextPath}/auth/logout" class="btn-logout">Logout</a>
        </div>
    </div>
    
    <div class="container">
        <h1>Upload Study Material</h1>
        
        <% if (request.getAttribute("error") != null) { %>
            <div class="error-message">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
        
        <% if (request.getAttribute("success") != null) { %>
            <div class="success-message">
                <%= request.getAttribute("success") %>
            </div>
        <% } %>
        
        <form method="POST" action="${pageContext.request.contextPath}/materials/upload" enctype="multipart/form-data" class="upload-form">
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" required>
            </div>
            
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" rows="4"></textarea>
            </div>
            
            <div class="form-group">
                <label for="subject">Subject:</label>
                <input type="text" id="subject" name="subject" required>
            </div>
            
            <div class="form-group">
                <label for="semester">Semester:</label>
                <input type="text" id="semester" name="semester" required>
            </div>
            
            <div class="form-group">
                <label for="file">Select File (PDF, DOC, PPT):</label>
                <input type="file" id="file" name="file" accept=".pdf,.doc,.docx,.ppt,.pptx" required>
            </div>
            
            <button type="submit" class="btn-primary">Upload</button>
        </form>
    </div>
</body>
</html>
