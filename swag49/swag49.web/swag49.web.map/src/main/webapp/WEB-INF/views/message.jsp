<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Message</title>
</head>
<body>
<c:if test="${view == true}">
    <h1>[SWAG] Message View</h1>
    <c:if test="${message != null}">
        <table>
            <tr>
                <td>Send:</td>
                <td>${message.sent}</td>
            </tr>
            <tr>
                <td>Subject:</td>
                <td>${message.subject}</td>
            </tr>
            <tr>
                <td>Content:</td>
                <td>${message.content}</td>
            </tr>
            <tr>
                <td>Sender:</td>
                <td>${message.sender.username}</td>
            </tr>
            <tr>
                <td>Receiver:</td>
                <td>${message.receiver.username}</td>
            </tr>
        </table>
    </c:if>
    <c:if test="${message == null}">
        <p>Message doesn't exist!</p>
    </c:if>
    <ul>
        <li><a href="../index.html">Messaging-Management</a></li>
        <li><a href="../incoming.html">Incoming-Messages</a></li>
        <li><a href="../outgoing.html">Outgoing-Messages</a></li>
    </ul>
</c:if>
<c:if test="${view == false}">
    <h1>[SWAG] Create/Send Message</h1>
    <form:form method="post" action="send" commandName="message">
        <table>
            <tr>
                <td><form:label path="receiver.id">Receiver</form:label></td>
                <td><form:input path="receiver.id"/></td>
                <td style="color:red;0"><form:errors path="receiver"/></td>
            </tr>
            <tr>
                <td><form:label path="subject">Subject</form:label></td>
                <td><form:input path="subject"/></td>
                <td style="color:red;0"><form:errors path="subject"/></td>
            </tr>
            <tr>
                <td><form:label path="content">Content</form:label></td>
                <td><form:textarea path="content" rows="5" cols="30"/></td>
                <td style="color:red;0"><form:errors path="content"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Send"/>
                </td>
            </tr>
        </table>
    </form:form>
    <ul>
        <li><a href="index.html">Back</a></li>
    </ul>
</c:if>
</body>
</html>
