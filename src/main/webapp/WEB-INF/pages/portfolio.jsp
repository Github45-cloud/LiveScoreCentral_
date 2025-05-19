<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Portfolio - LiveScore</title>
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/header.css" />
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/portfolio.css" />
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/footer.css" />
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="portfolio-body">
        <h2>Our Portfolio</h2>
        <p>Here's a quick look into what we've achieved so far at LiveScore.</p>

        <div class="portfolio-grid">
            <div class="portfolio-card">
                <h3>Live Match Tracker</h3>
                <p>We built a robust system that tracks live football matches in real-time with accuracy and speed.</p>
            </div>
            <div class="portfolio-card">
                <h3>Data Insights Engine</h3>
                <p>Our system analyzes weekly performance of players and clubs, producing accurate insights and stats.</p>
            </div>
            <div class="portfolio-card">
                <h3>User-Focused Dashboard</h3>
                <p>We designed an intuitive dashboard showing weekly rankings, charts, and player highlights.</p>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
