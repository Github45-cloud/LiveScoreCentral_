<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Feedback - LiveScore</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin-feedback.css" />
</head>
<body>

<jsp:include page="adminheader.jsp" />

<div class="feedback-container">
    <h1>Customer Feedback</h1>

    <div class="feedback-list">
        <c:choose>
            <c:when test="${not empty feedbackList}">
                <c:forEach var="f" items="${feedbackList}">
    <div class="feedback-card">
        <h3>${f.name}</h3>
        <p><strong>Email:</strong> ${f.email}</p>
        <p><strong>Message:</strong> ${f.message}</p>
        <p><em>Submitted on: ${f.submittedAt}</em></p>

        <form action="${pageContext.request.contextPath}/deleteFeedback" method="post" onsubmit="return confirm('Are you sure you want to delete this feedback?');">
            <input type="hidden" name="feedbackId" value="${f.feedbackId}" />
            <button type="submit" class="delete-btn">Delete</button>
        </form>
    </div>
</c:forEach>

            </c:when>
            <c:otherwise>
                <p>No feedback submitted yet.</p>
            </c:otherwise>
        </c:choose>
    </div>
</div>



<jsp:include page="adminfooter.jsp" />

</body>
</html>
