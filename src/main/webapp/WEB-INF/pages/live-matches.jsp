<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.LiveScore.model.MatchModel" %>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Live Matches - LiveScore</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/live-matches.css" />
</head>
<body>

<jsp:include page="header.jsp" />

<div class="main-body">
    <section class="live-matches-section">
        <h2>Current Live Matches</h2>

        <div class="matches-container">
            <c:choose>
                <c:when test="${not empty matches}">
                    <c:forEach var="match" items="${matches}">
                        <div class="match-card">
                            <div class="teams">
                                <span class="team">${match.homeTeam}</span>
                                <span class="score">${match.homeScore} - ${match.awayScore}</span>
                                <span class="team">${match.awayTeam}</span>
                            </div>
                            <div class="match-time">
                                <c:choose>
                                    <c:when test="${not empty match.kickoff}">
                                        ${match.kickoff}
                                    </c:when>
                                    <c:otherwise>
                                        Kickoff time not available
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="location">
                                <strong>Stadium:</strong> ${match.stadium}<br>
                                <strong>Location:</strong> ${match.location}
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>No live matches available at the moment.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="back-button-container">
            <a href="${pageContext.request.contextPath}/home" class="back-button">Back</a>
        </div>
    </section>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>
