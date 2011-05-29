<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>
    Register User
</h1>
<form:form method="post" action="doUserRegistration" commandName="userDTO">
    <table>
        <tr>
            <td><form:label path="firstName">First Name</form:label></td>
            <td><form:input path="firstName"/></td>
        </tr>
        <tr>
            <td><form:label path="lastName">Last Name</form:label></td>
            <td><form:input path="lastName"/></td>
        </tr>
        <tr>
            <td><form:label path="city">City</form:label></td>
            <td><form:input path="city"/></td>
        </tr>
        <tr>
            <td><form:label path="zipCode">Zip-Code</form:label></td>
            <td><form:input path="zipCode"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Register User"/>
            </td>
        </tr>
    </table>
</form:form>
<h1>
    Registered Users
</h1>
<c:if test="${!empty userList}">
    <table class="data">
        <tr>
            <th>Name</th>
            <th>City</th>
            <th>Zip-Code</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.lastName}, ${user.firstName} </td>
                <td>${user.city}</td>
                <td>${user.zipCode}</td>

                <td><a href="delete/${user.id}">delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
