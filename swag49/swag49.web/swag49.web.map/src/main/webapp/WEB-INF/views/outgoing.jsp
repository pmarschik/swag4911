<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Outgoing Messages</title>
</head>
<body>
<h1>[SWAG] Outgoing Messages</h1>
<c:if test="${user == null}">
    <h3>You have to register or login!</h3>
</c:if>
<c:if test="${user != null}">
    <h3>You are logged in as: ${user}</h3>
    <ul class="messagingMenu">
        <li><a class="messageLink" href="../messaging/incoming.html">Incoming Messages</a></li>
        <li><a href="../messaging/outgoing.html">Outgoing Messages</a></li>
        <li><a class="messageLink" href="../messaging/message.html">New message</a></li>
    </ul>
</c:if>
<c:if test="${!empty outgoingMessages}">
    <table class="data">
        <tr>
            <th>Receiver</th>
            <th>Subject</th>
            <th>Send</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${outgoingMessages}" var="message">
            <tr>
                <td>${message.receiver}</td>
                <td>${message.subject}</td>
                <td>${message.sent}</td>
                <td><a href="view/${message.id}">View</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
