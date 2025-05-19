<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Match - Admin</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/update-match.css" />
</head>
<body>

    <jsp:include page="adminheader.jsp" />

    <!-- Back Button -->
    <div class="back-button">
        <a href="<%= contextPath %>/adminHome" class="btn-back">Back</a>
    </div>

    <div class="update-match-container">
        <h1>Update Match Details</h1>
        <%-- Display error message if exists --%>
<c:if test="${not empty error}">
    <div class="error-message">${error}</div>
</c:if>

<%-- Display success message if exists --%>
<c:if test="${not empty message}">
    <div class="success-message">${message}</div>
</c:if>
        
        <form action="<%= contextPath %>/admin/updateMatch" method="post" class="update-match-form">

            <label for="matchId">Match ID:</label>
            <input type="text" id="matchId" name="matchId" required placeholder="Enter Match ID">

            <label for="homeScore">Home Team Score:</label>
            <input type="number" id="homeScore" name="homeScore" required>

            <label for="awayScore">Away Team Score:</label>
            <input type="number" id="awayScore" name="awayScore" required>

            <label for="status">Match Status:</label>
            <select id="status" name="status" required>
                <option value="Upcoming">Upcoming</option>
                <option value="Live">Live</option>
                <option value="Interval">Interval</option>
                <option value="Finished">Finished</option>
                <option value="Postponed">Postponed</option>
            </select>

            <input type="submit" value="Update Match" class="btn-submit">

        </form>
    </div>

    <jsp:include page="adminfooter.jsp" />

</body>
</html>
