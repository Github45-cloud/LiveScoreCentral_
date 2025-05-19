<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contact Us - LiveScore</title>
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/header.css" />
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/contact.css" />
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/footer.css" />
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="contact-body">
        <h2>Contact Us</h2>
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
<% String successMessage = (String) request.getAttribute("successMessage"); %>

<% if (errorMessage != null) { %>
    <p class="error-message"><%= errorMessage %></p>
<% } %>

<% if (successMessage != null) { %>
    <p class="success-message"><%= successMessage %></p>
<% } %>
        
        <p>We’re here to help! Reach out for feedback, questions, or support.</p>

        <div class="contact-container">
            <form action="<%= contextPath %>/submitFeedback" method="post" class="contact-form">
            
                <label for="name">Name</label>
                <input type="text" id="name" name="name" placeholder="Your name" required>

                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Your email" required>

                <label for="message">Message</label>
                <textarea id="message" name="message" placeholder="Write your message here..." rows="5" required></textarea>

                <button type="submit">Send Message</button>
            </form>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
