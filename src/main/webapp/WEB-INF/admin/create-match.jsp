
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>



<%
    String contextPath = request.getContextPath();
    String error = (String) request.getAttribute("error");
    String success = (String) request.getAttribute("success");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Match</title>
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/register.css" />
</head>
<body>

<div class="back-button">
        <a href="<%= contextPath %>/adminHome" class="btn-back">Back</a>
    </div>
    <div class="container">
        <h1>Create Match</h1>

        <% if (error != null && !error.isEmpty()) { %>
            <p class="error-message"><%= error %></p>
        <% } %>

        <% if (success != null && !success.isEmpty()) { %>
            <p class="success-message"><%= success %></p>
        <% } %>

        <form action="<%= contextPath %>/create-match" method="post">
            <div class="row">
                <div class="col">
                    <label for="matchId">Match ID:</label>
                    <input type="text" id="matchId" name="matchId" required>
                </div>
                <div class="col">
                    <label for="kickoff">Kickoff:</label>
                    <input type="datetime-local" id="kickoff" name="kickoff" required>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <label for="homeTeam">Home Team:</label>
                    <input type="text" id="homeTeam" name="homeTeam" required>
                </div>
                <div class="col">
                    <label for="awayTeam">Away Team:</label>
                    <input type="text" id="awayTeam" name="awayTeam" required>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <label for="homeScore">Home Score:</label>
                    <input type="number" id="homeScore" name="homeScore" required>
                </div>
                <div class="col">
                    <label for="awayScore">Away Score:</label>
                    <input type="number" id="awayScore" name="awayScore" required>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <label for="location">Location:</label>
                    <input type="text" id="location" name="location" required>
                </div>
                <div class="col">
                    <label for="stadium">Stadium:</label>
                    <input type="text" id="stadium" name="stadium" required>
                </div>
            </div>

            <div class="btn-row">
                <button type="submit">Create Match</button>
            </div>
        </form>
    </div>

</body>
</html>
