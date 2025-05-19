<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>About Us - LiveScore</title>
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/header.css" />
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/about.css" />
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/footer.css" />
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="about-container">
        <section class="about-header">
            <h2>About LiveScore</h2>
            <p>LiveScore provides real-time football match updates, scores, and detailed statistics. Whether you're following the Premier League, La Liga, Serie A, or any other major league, we deliver the most accurate and up-to-date match information. Our platform aims to make sure that every football fan stays connected to the beautiful game, no matter where they are.</p>
        </section>

        <section class="about-features">
            <h3>How It Works</h3>
            <div class="feature-box">
                <div class="feature-item">
                    <h4>Live Score Updates</h4>
                    <p>We provide real-time score updates for ongoing football matches from around the world. Our platform ensures you never miss a moment of action, whether you're on the go or sitting at home.</p>
                </div>
                <div class="feature-item">
                    <h4>Match Highlights</h4>
                    <p>Watch key match events, such as goals, assists, and red cards, with detailed statistics for each player. Get insights into match performance for each team involved.</p>
                </div>
                <div class="feature-item">
                    <h4>Team Stats</h4>
                    <p>View up-to-date team stats, including wins, losses, draws, and more. We keep track how the most in form temas of all around the europian football.</p>
                </div>
            </div>
        </section>

        <section class="about-vision">
            <h3>Our Vision</h3>
            <p>Our mission is to bring fans closer to the sport by providing them with real-time football updates and statistics. LiveScore is more than just scores – it's the heartbeat of football, connecting fans globally through a seamless experience.</p>
        </section>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
