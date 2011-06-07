<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>User Overview</title>
</head>
<body>
<h1>Welcome to SWAG</h1>
<spring:url value="/resources/images" var="resourcePath"/>
<img src="${resourcePath}/test.jpg" width="800" height="200"/>
<c:if test="${loggedInUser == null}">
    <h3>You have to register or login!</h3>
    <ul>
        <li><a href="register.html">Register</a></li>
        <li><a href="login.html">Login</a></li>
    </ul>
</c:if>
<c:if test="${loggedInUser != null}">
    <h3>You are logged in as: ${loggedInUser.username}</h3>
    <ul>
        <li><a href="logout.html">Logout</a></li>
        <li><a href="edit.html">Edit</a></li>
        <li><a href="maps.html">Maps</a></li>
    </ul>
</c:if>
</body>
</html>
