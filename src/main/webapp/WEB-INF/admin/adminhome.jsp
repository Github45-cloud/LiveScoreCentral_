<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - LiveScore</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminheader.css" />
    <<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminhome.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminfooter.css" />
</head>
<body>

    <jsp:include page="adminheader.jsp" />

    <div class="admin-dashboard">
        <h1>Admin Dashboard</h1>
        <div class="admin-actions">
            <div class="admin-action-card">
    <h2>Create Match</h2>
    <p>Add a new match to the system.</p>
    <a href="<%= contextPath %>/admin/createMatchPage" class="btn">Go to Create Match</a>
</div>

<div class="admin-action-card">
    <h2>Update Match</h2>
    <p>Update existing match details.</p>
    <a href="<%= contextPath %>/admin/updateMatchPage" class="btn">Go to Update Match</a>
</div>

<div class="admin-action-card">
    <h2>Delete Match</h2>
    <p>Remove a match from the system.</p>
    <a href="<%= contextPath %>/admin/deleteMatchPage" class="btn">Go to Delete Match</a>
</div>
</div>
</div>

    <jsp:include page="adminfooter.jsp" />

</body>
</html>
