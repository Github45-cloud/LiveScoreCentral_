<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>

<%
    HttpSession userSession = request.getSession(false);
    String currentUser = (String) (userSession != null ? userSession.getAttribute("username") : null);
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - LiveScore</title>
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/adminheader.css">
</head>
<body>
    <div id="admin-header">
        <header class="admin-header">
            <h1 class="logo">
                <a href="<%= contextPath %>/adminHome">
                    <img src="<%= contextPath %>/images/system/logo.png" alt="LiveScore Logo" />
                </a>
            </h1>
            <nav>
                <ul class="admin-nav">
                    <li><a href="<%= contextPath %>/adminHome">Home</a></li>
                    <li><a href="<%= contextPath %>/adminFeedback">Feedback</a></li>
                    <li><a href="<%= contextPath %>/admin/updateWeeklyInsights">Update Weekly Insights</a></li>
                    <li>
    					<form action="<%= request.getContextPath() %>/logout" method="post">
        				<input type="submit" value="Logout" />
    				</form>
				</li>
                </ul>
            </nav>
        </header>
    </div>
</body>
</html>
