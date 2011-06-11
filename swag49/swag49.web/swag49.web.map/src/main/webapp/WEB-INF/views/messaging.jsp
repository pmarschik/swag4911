<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Messaging</title>
</head>
<body>
<h1>[SWAG] Messaging</h1>
<div id="message"></div>
<c:if test="${user == null}">
    <h3>You have to register or login!</h3>
</c:if>
<c:if test="${user != null}">
    <h3>You are logged in as: ${user}</h3>
    <ul class="messagingMenu">
        <li><a class="messageLink" href="../messaging/incoming.html">Incoming Messages</a></li>
        <li><a href="../messaging/outgoing.html">Outgoing Messages</a></li>
        <li><a class="messageLink" href="../messaging/message.html">New message</a></li>
    </ul>
</c:if>
<script type="text/javascript">
    $("a").live("click", function () {
        $.get($(this).attr("href"),function (result) {
            $("#content").html(result);
        });

        return false;
    });
</script>

</body>
</html>
