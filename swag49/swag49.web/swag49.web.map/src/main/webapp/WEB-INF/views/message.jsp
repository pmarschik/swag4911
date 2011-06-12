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
        <form:form commandName="message">
            <table>
                <tr>
                    <td>Sent</td>
                    <td><form:textarea path="sent" rows="1" cols="20" readonly="true"/></td>
                </tr>
                <tr>
                    <td>Received</td>
                    <td><form:textarea path="received" rows="1" cols="20" readonly="true"/></td>
                </tr>
                <tr>
                    <td>Sender</td>
                    <td><form:textarea path="sender.username" rows="2" cols="20" readonly="true"/></td>
                </tr>
                <tr>
                    <td>Receiver</td>
                    <td><form:textarea path="receiver.username" rows="2" cols="20" readonly="true"/></td>
                </tr>
                <tr>
                    <td>Subject</td>
                    <td><form:textarea path="subject" rows="2" cols="20" readonly="true"/></td>
                </tr>
                <tr>
                    <td>Content</td>
                    <td><form:textarea path="content" rows="5" cols="40" readonly="true"/></td>
                </tr>
            </table>
        </form:form>
    </c:if>
    <c:if test="${message == null}">
        <p>Message doesn't exist!</p>
    </c:if>
    <ul>
        <li><a class="contentLink"  href="../messaging/incoming.html">Incoming-Messages</a></li>
        <li><a class="contentLink"  href="../messaging/outgoing.html">Outgoing-Messages</a></li>
    </ul>
</c:if>

<c:if test="${view == false}">
    <h1>[SWAG] Create/Send Message</h1>
    <form:form method="post" action="../messaging/send" commandName="message">
        <table>
            <tr>
                <td><form:label path="receiver.username">Receiver</form:label></td>
                <td><form:input path="receiver.username" id="username" /></td>
                <td style="color:red;0"><form:errors path="receiver"/></td>
            </tr>
            <tr>
                <td><form:label path="subject">Subject</form:label></td>
                <td><form:input path="subject" id="subject"/></td>
                <td style="color:red;0"><form:errors path="subject"/></td>
            </tr>
            <tr>
                <td><form:label path="content">Content</form:label></td>
                <td><form:textarea path="content" id="content" rows="5" cols="40"/></td>
                <td style="color:red;0"><form:errors path="content"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input class="button" type="submit" value="Send"/>
                </td>
            </tr>
        </table>
    </form:form>
</c:if>
<script type="text/javascript">
    $(function()
    {
        $(".button").click(function()
        {

            var username = $("input#username").val();
            var subject = $("input#subject").val();
            var content = $("textarea#content").val();
            var dataString = 'username='+ username + '&subject=' + subject + '&content=' + content;

            $.ajax({
                type: "POST",
                url: "../messaging/send.html",
                data: {username : username, subject: subject, content: content},
                    success: function()
                    {
                        $('#message').html("<h2>Message sent!</h2>");
                    }
            });

            return false;
        });
    });
 </script>
</body>
</html>
