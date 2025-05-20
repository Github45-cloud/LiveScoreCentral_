<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.LiveScore.model.UserModel" %>

<%
    HttpSession userSession = request.getSession(false);
    UserModel currentUser = (userSession != null) ? 
        (UserModel) userSession.getAttribute("user") : null;
%>
<%
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>LiveScore</title>
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/resources/css/header.css">
</head>
<body>
    <div id="header">
        <header class="header">
            <h1 class="logo">
    <a href="<%= contextPath %>">
        <img src="<%= contextPath %>/random.png" alt="LiveScore Logo" />
    </a>
</h1>


            <nav>
                <ul class="main-nav">
                    <li><a href="<%= contextPath %>/home">Home</a></li>
                    <li><a href="<%= contextPath %>/about">About</a></li>
                    <li><a href="<%= contextPath %>/portfolio">Portfolio</a></li>
                    <li><a href="<%= contextPath %>/contact">Contact</a></li>
                    
                    <li>
                        <% if (currentUser != null) { %>
                            <form action="<%= contextPath %>/logout" method="post">
                                <input type="submit" value="Logout" />
                            </form>
                        <% } else { %>
                            <form action="<%= contextPath %>/login" method="get">
                                <input type="submit" value="Login" />
                            </form>
                        <% } %>
                    </li>

                    <% if (currentUser != null) { %>
    <li style="color: white; padding: 10px;"><%= currentUser.getFirstName() %>!</li>
<% } %>


                </ul>
            </nav>
        </header>
    </div>
</body>
</html>
