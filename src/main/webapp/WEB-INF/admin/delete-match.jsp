<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>

<%
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Match - Admin</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/delete-match.css" />
</head>
<body>

    <jsp:include page="adminheader.jsp" />

    <!-- Back Button -->
    <div class="back-button">
        <a href="<%= contextPath %>/adminHome" class="btn-back">Back</a>
    </div>

    <div class="delete-match-container">
        <h1>Delete Match</h1>
        <c:if test="${not empty error}">
    <div class="error-message">${error}</div>
</c:if>

<c:if test="${not empty message}">
    <div class="success-message">${message}</div>
</c:if>
        
        <form action="<%= contextPath %>/admin/deleteMatch" method="post" class="delete-match-form">

            <label for="matchId">Match ID:</label>
            <input type="text" id="matchId" name="matchId" required placeholder="Enter Match ID to delete">

            <input type="submit" value="Delete Match" class="btn-submit">

        </form>
    </div>

    <jsp:include page="adminfooter.jsp" />

</body>
</html>
