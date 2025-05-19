<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    String contextPath = request.getContextPath();
    String error = (String) request.getAttribute("errorMessages"); 
    String success = (String) request.getAttribute("success");
%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Form</title>
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/register.css" />
</head>
<body>
    <div class="container">
        <h1>Registration Form</h1>

        <% if (error != null && !error.isEmpty()) { %>
    <p class="error-message"><%= error %></p>
<% } %>


        <% if (success != null && !success.isEmpty()) { %>
            <p class="success-message"><%= success %></p>
        <% } %>

        <form action="<%= contextPath %>/register" method="post" enctype="multipart/form-data">
            <div class="row">
                <div class="col">
                    <label for="firstName">First Name:</label>
                    <input type="text" id="firstName" name="firstName" required>
                </div>
                <div class="col">
                    <label for="lastName">Last Name:</label>
                    <input type="text" id="lastName" name="lastName" required>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="col">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="col">
                    <label for="profilePicture">Profile Picture:</label>
                    <input type="file" id="profilePicture" name="profilePicture">
                </div>
            </div>
            <div class="btn-row">
                <button type="submit">Register</button>
            </div>
        </form>
    </div>
</body>
</html>
