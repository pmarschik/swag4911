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
<ul>
    <li><a href="index.html">Back</a></li>
</ul>
</body>
</html>
