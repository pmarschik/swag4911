<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Statistics</title>
</head>
<body>
<h1>[SWAG] Statistics</h1>
<h3>Choose a statistic:</h3>
<c:if test="${!empty statistics}">
    <ul>
        <c:forEach items="${statistics}" var="statistic">
            <li><a href="../statistics/${statistic.id}.html">${statistic.name}</a></li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>
