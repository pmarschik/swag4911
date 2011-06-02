<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Messaging</title>
</head>
<body>
<h1>[SWAG] Messaging</h1>
<c:if test="${user == null}">
    <h3>You have to register or login!</h3>
    <ul>
        <li><a href="user.html">User-Management</a></li>
    </ul>
</c:if>
<c:if test="${user != null}">
    <h3>You are logged in as: ${user.username}</h3>
    <ul>
        <li><a href="incoming.html">Incoming Messages</a></li>
        <li><a href="outgoing.html">Outgoing Messages</a></li>
        <li><a href="message.html">New message</a></li>
        <li><a href="user.html">User-Management</a></li>
    </ul>
</c:if>
</body>
</html>
