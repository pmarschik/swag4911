<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Map View</title>
</head>
<body>
<h1> 
	[SWAG] Map View
</h1>
<c:if test="${userID == null}">
    <p>You have to login or register first!</p>
    <ul>
        <li>TODO</li>
    </ul>
</c:if>
<c:if test="${userID != null}">
    <p>Welcome to [SWAG] user with id: ${userID}</p>
    <ul>
        <li><a href="messaging.html">Messaging</a></li>
    </ul>
    <ul>
        <li><a href="statistics.html">Statistics</a></li>
    </ul>
</c:if>
</body>
</html>
