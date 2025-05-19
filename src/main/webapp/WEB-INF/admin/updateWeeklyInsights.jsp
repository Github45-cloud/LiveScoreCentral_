<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Weekly Insights - Admin</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/update-weekly-insights.css" />
</head>
<body>
<%
    String error = (String) request.getAttribute("errorMessages");
    String success = (String) request.getAttribute("success");
%>

<% if (error != null && !error.isEmpty()) { %>
    <p class="error-message"><%= error %></p>
<% } %>

<% if (success != null && !success.isEmpty()) { %>
    <p class="success-message"><%= success %></p>
<% } %>

<jsp:include page="adminheader.jsp" />


<div class="update-insights-container">

<!-- Back Button -->
<div class="back-button">
    <a href="<%= contextPath %>/adminHome">← Back to Admin Home</a>
</div>

    <h1>Update Weekly Insights</h1>

    <!-- Match Result Distribution -->
    <section class="insight-section">
        <h2>Match Result Distribution</h2>
        <c:if test="${not empty error}">
    <p class="error-message">${error}</p>
</c:if>

<c:if test="${not empty success}">
    <p class="success-message">${success}</p>
</c:if>
        
        <form action="<%= contextPath %>/admin/updateResults" method="post" class="update-form">
            <label for="homeWins">Home Wins (%):</label>
            <input type="number" id="homeWins" name="homeWins" required>

            <label for="awayWins">Away Wins (%):</label>
            <input type="number" id="awayWins" name="awayWins" required>

            <label for="draws">Draws (%):</label>
            <input type="number" id="draws" name="draws" required>

            <input type="submit" value="Update Result Distribution" class="btn-submit">
        </form>
    </section>

    <!-- Power Rankings -->
    <section class="insight-section">
        <h2>Power Rankings</h2>
     <c:if test="${not empty error}">
    <p class="error-message">${error}</p>
</c:if>

<c:if test="${not empty success}">
    <p class="success-message">${success}</p>
</c:if>
        <form action="<%= contextPath %>/admin/updateRankings" method="post" class="update-form">
            <% for (int i = 1; i <= 10; i++) { %>
                <div class="ranking-entry">
                    <label>Team <%= i %> Name:</label>
                    <input type="text" name="team<%= i %>Name" required>

                    <label>Recent Results (W/L/D):</label>
                    <input type="text" name="team<%= i %>Results" placeholder="e.g., W W L D W" required>
                </div>
            <% } %>

            <input type="submit" value="Update Power Rankings" class="btn-submit">
        </form>
    </section>

    <!-- Highlights of This Week -->
    <section class="insight-section">
        <h2>Highlights of This Week</h2>
        <c:if test="${not empty error}">
    <p class="error-message">${error}</p>
</c:if>

<c:if test="${not empty success}">
    <p class="success-message">${success}</p>
</c:if>
        <form action="<%= contextPath %>/admin/updateHighlights" method="post" class="update-form">

            <label>Top Goalscorer:</label>
            <input type="text" name="topGoalscorer" required>

            <label>Top Assists:</label>
            <input type="text" name="topAssists" required>

            <label>Most Saves:</label>
            <input type="text" name="mostSaves" required>

            <label>Most Interceptions:</label>
            <input type="text" name="mostInterceptions" required>

            <label>Most Yellow Cards:</label>
            <input type="text" name="mostYellowCards" required>

            <label>Most Red Cards:</label>
            <input type="text" name="mostRedCards" required>

            <input type="submit" value="Update Highlights" class="btn-submit">
        </form>
    </section>

</div>

<jsp:include page="adminfooter.jsp" />

</body>
</html>
