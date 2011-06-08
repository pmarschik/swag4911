<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>
    Login User
</h1>
<spring:url value="/resources/images" var="resourcePath"/>
<img src="${resourcePath}/test.jpg" width="800" height="200"/>
<form:form method="post" action="login" commandName="user">
    <table>
        <tr>
            <td><form:label path="username">Username</form:label></td>
            <td><form:input path="username"/></td>
            <td><FONT color="red"><form:errors path="username" /></FONT></td>
        </tr>
        <tr>
            <td><form:label path="password">Password</form:label></td>
            <td><form:password path="password"/></td>
            <td><FONT color="red"><form:errors path="password" /></FONT></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Login User"/>
            </td>
        </tr>
    </table>
</form:form>
<c:if test="${loginError != null}">
    <p><FONT color="red">Error: ${loginError}</FONT></p>
</c:if>

<ul>
    <li><a href="register.html">Register</a></li>
    <li><a href="overview.html">Overview</a></li>
</ul>

</body>
</html>
