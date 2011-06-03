<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Edit Profile</title>
</head>
<body>
<h1>
    Edit Profile
</h1>
<form:form method="post" action="edit" commandName="user">
    <table>
        <tr>
            <td><form:label path="username">UserName</form:label></td>
            <td><form:input path="username"/></td>
            <td><FONT color="red"><form:errors path="username" /></FONT></td>
        </tr>
        <tr>
            <td><form:label path="password">Password</form:label></td>
            <td><form:password path="password"/></td>
            <td><FONT color="red"><form:errors path="password" /></FONT></td>
        </tr>
        <tr>
            <td><form:label path="firstName">First Name</form:label></td>
            <td><form:input path="firstName"/></td>
            <td><FONT color="red"><form:errors path="firstName" /></FONT></td>
        </tr>
        <tr>
            <td><form:label path="lastName">Last Name</form:label></td>
            <td><form:input path="lastName"/></td>
            <td><FONT color="red"><form:errors path="lastName" /></FONT></td>
        </tr>
        <tr>
            <td><form:label path="email">Email</form:label></td>
            <td><form:input path="email"/></td>
            <td><FONT color="red"><form:errors path="email" /></FONT></td>
        </tr>
        <tr>
            <td><form:label path="state">State</form:label></td>
            <td><form:input path="state"/></td>
        </tr>
        <tr>
            <td><form:label path="city">City</form:label></td>
            <td><form:input path="city"/></td>
        </tr>
        <tr>
            <td><form:label path="postalCode">Postal Code</form:label></td>
            <td><form:input path="postalCode"/></td>
        </tr>
        <tr>
            <td><form:label path="street">Street</form:label></td>
            <td><form:input path="street"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Edit Profile"/>
            </td>
        </tr>
    </table>
</form:form>

<ul>
    <li><a href="overview.html">Overview</a></li>
</ul>
</body>
</html>
