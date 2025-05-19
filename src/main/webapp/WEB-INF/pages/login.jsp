<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String error = (String) request.getAttribute("error");
    String success = (String) request.getAttribute("success");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login to your account</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" />
</head>
<body>
    <div class="login-box">
        <h2>Login</h2>

        <% if (error != null && !error.isEmpty()) { %>
            <p class="error-message"><%= error %></p>
        <% } %>

        <% if (success != null && !success.isEmpty()) { %>
            <p class="success-message"><%= success %></p>
        <% } %>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <button type="submit" class="login-button">Login</button>
        </form>

        <!-- Register section -->
        <div style="text-align: center; margin-top: 20px;">
            <p>Don't have an account? <a href="${pageContext.request.contextPath}/register" class="register-link">Register here</a></p>
        </div>
    </div>
</body>
</html>