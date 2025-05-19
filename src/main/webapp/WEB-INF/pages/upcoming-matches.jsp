<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Upcoming Matches - LiveScore</title>
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/header.css" />
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/footer.css" />
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/upcoming-matches.css" />
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="main-body">
        <section class="upcoming-matches-section">
            <h2>Upcoming Matches</h2>

            <div class="matches-container">
                <c:if test="${not empty matches}">
                    <c:forEach var="match" items="${matches}">
                        <div class="match-card">
                            <div class="teams">
                                <span class="team">${match.homeTeam}</span> vs 
                                <span class="team">${match.awayTeam}</span>
                            </div>
                            <div class="match-time">
                                <fmt:formatDate value="${match.kickoff}" pattern="EEEE, MMM d, yyyy - hh:mm a" />
                            </div>
                            <div class="match-location">
                                ${match.stadium}, ${match.location}
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${empty matches}">
                    <p>No upcoming matches at the moment.</p>
                </c:if>
            </div>

            <div class="back-button-container">
                <a href="<%= contextPath %>/home" class="back-button">Back</a>
            </div>

        </section>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
