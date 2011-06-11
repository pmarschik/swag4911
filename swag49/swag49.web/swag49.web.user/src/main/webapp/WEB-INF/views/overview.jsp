<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<spring:url value="/resources" var="resourcePath"/>
<html>
<head>
    <title>User Overview</title>
    <script type="text/javascript" src="${resourcePath}/js/jquery-1.5.1.min.js"></script>
    <script type="text/javascript" src="${resourcePath}/js/jquery-ui-1.8.13.custom.min.js"></script>
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
        <li><a id="logout" href="logout.html">Logout</a></li>
        <li><a href="edit.html">Edit</a></li>
        <li><a href="maps.html">Maps</a></li>
    </ul>
</c:if>
<c:forEach items="${mapLocations}" var="location">
    <input class="url" type="hidden" value="${location}"/>
</c:forEach>
<script type="text/javascript">
    $("#logout").live("click", function () {
        $(".url").each(function(i) {
            $.get(this.value)
        });
        $.load($(this).attr("href"));
    });
</script>
</body>
</html>
