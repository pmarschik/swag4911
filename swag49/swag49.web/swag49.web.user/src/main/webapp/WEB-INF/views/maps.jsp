<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Map</title>
</head>
<body>
<h1>
    [SWAG] Map
</h1>

<h2>
    Available Maps
</h2>
<c:if test="${!empty availableMapLocations}">
    <table class="data">
        <tr>
            <th>Id</th>
            <th>URL</th>
            <th>Map-Name</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${availableMapLocations}" var="mapLocation">
            <tr>
                <td>${mapLocation.id}</td>
                <td>${mapLocation.url}</td>
                <td>${mapLocation.mapName}</td>
                <td><a href="join/${mapLocation.id}">join</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h2>
    My Maps
</h2>
<c:if test="${!empty myMapLocations}">
    <table class="data">
        <tr>
            <th>Id</th>
            <th>URL</th>
            <th>Map-Name</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${myMapLocations}" var="mapLocation">
            <tr>
                <td>${mapLocation.id}</td>
                <td>${mapLocation.url}</td>
                <td>${mapLocation.mapName}</td>
                <td><a href="play/${mapLocation.id}">play</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<ul>
    <li><a href="overview.html">Overview</a></li>
</ul>
</body>
</html>
