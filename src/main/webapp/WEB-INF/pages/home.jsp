<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Home - LiveScore</title>
    <link rel="stylesheet" href="<%= contextPath %>/css/header.css" />
    <link rel="stylesheet" href="<%= contextPath %>/css/home.css" />
    <link rel="stylesheet" href="<%= contextPath %>/css/footer.css" />
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="main-body">
        <section class="dashboard">
            <h2>Welcome to LiveScoreCentral!</h2>
            <div class="card-container">
                <div class="card">
                    <h3>View Matches</h3>
                    <p>Check live scores and match updates easily.</p>
                    <a href="${pageContext.request.contextPath}/live-matches">View Now</a>
                </div>
                <div class="card">
                    <h3>Upcoming Matches</h3>
                    <p>Stay updated on upcoming tournaments and leagues.</p>
                    <a href="${pageContext.request.contextPath}/upcoming-matches">View Now</a>
                </div>
            </div>
        </section>

        <section class="match-dashboard">
            <h2>Weekly Match Insights</h2>
            <div class="dashboard-grid">

                <!-- Match Result Distribution Chart -->
                <div class="dashboard-card chart-section">
                    <h3>Match Result Distribution</h3>
                    <canvas id="matchChart"></canvas>
                </div>

                <!-- Power Rankings -->
                <div class="dashboard-card">
                    <h3>This Week's Power Rankings</h3>
                    <ol class="power-rankings">
                        <c:choose>
                            <c:when test="${not empty insight.powerRankings}">
                                <c:forEach var="r" items="${insight.powerRankings}">
                                    <li>${r.teamName}
                                        <div class="form">
                                            <c:forEach var="i" begin="0" end="${fn:length(r.recentResults) - 1}">
                                                <c:set var="res" value="${fn:substring(r.recentResults, i, i + 1)}"/>
                                                <span class="${res eq 'W' ? 'win' : (res eq 'L' ? 'lose' : 'draw')}">${res}</span>
                                            </c:forEach>
                                        </div>
                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <li>No data available</li>
                            </c:otherwise>
                        </c:choose>
                    </ol>
                </div>

                <!-- Highlights -->
                <div class="dashboard-card">
                    <h3>Highlights of This Week</h3>
                    <ul class="highlights">
                        <c:choose>
                            <c:when test="${not empty insight.highlights}">
                                <c:if test="${not empty insight.highlights.topGoalscorer}">
                                    <li>Top Goalscorer: ${insight.highlights.topGoalscorer}</li>
                                </c:if>
                                <c:if test="${not empty insight.highlights.topAssists}">
                                    <li>Top Assists: ${insight.highlights.topAssists}</li>
                                </c:if>
                                <c:if test="${not empty insight.highlights.mostSaves}">
                                    <li>Most Saves: ${insight.highlights.mostSaves}</li>
                                </c:if>
                                <c:if test="${not empty insight.highlights.mostInterceptions}">
                                    <li>Most Interceptions: ${insight.highlights.mostInterceptions}</li>
                                </c:if>
                                <c:if test="${not empty insight.highlights.mostYellowCards}">
                                    <li>Most Yellow Cards: ${insight.highlights.mostYellowCards}</li>
                                </c:if>
                                <c:if test="${not empty insight.highlights.mostRedCards}">
                                    <li>Most Red Cards: ${insight.highlights.mostRedCards}</li>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <li>No highlights available</li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>

            </div>
        </section>

        
    </div>

    <!-- Chart JS -->
    <script>
        const ctx = document.getElementById('matchChart').getContext('2d');
        const matchChart = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: ['Home Wins', 'Away Wins', 'Draws'],
                datasets: [{
                    data: [
                        ${insight.resultDistribution != null ? insight.resultDistribution.homeWins : 0},
                        ${insight.resultDistribution != null ? insight.resultDistribution.awayWins : 0},
                        ${insight.resultDistribution != null ? insight.resultDistribution.draws : 0}
                    ],
                    backgroundColor: ['#1f8a70', '#24527a', '#bbb132'],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true
            }
        });
    </script>

    <jsp:include page="footer.jsp" />
</body>
</html>
