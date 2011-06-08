<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Statistics</title>
</head>
<body>
<h1>[SWAG] Statistics</h1>

<h3>${statistic.name}</h3>

<table class="data">
    <tr>
        <th>Ranking</th>
        <th>Player</th>
        <th>Score</th>
    </tr>
    <c:forEach items="${statistic.entries}" var="entry">
        <tr>
            <td>${entry.ranking}</td>
            <td>${entry.player}</td>
            <td>${entry.score}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
