<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Statistics</title>
</head>
<body>
<h1>[SWAG] Statistics</h1>
<c:if test="${user == null}">
    <h3>You have to register or login!</h3>
    <ul>
        <li><a href="map.html">Back</a></li>
    </ul>
</c:if>
<c:if test="${user != null}">
    <h3>Choose your statistic:</h3>
    <c:if test="${!empty statistics}">
        <ul>
            <c:forEach items="${statistics}" var="statistic">
                <li><a href="${statistic.id}">${statistic.name}</a></li>
            </c:forEach>
        </ul>
    </c:if>
</c:if>
</body>
</html>
