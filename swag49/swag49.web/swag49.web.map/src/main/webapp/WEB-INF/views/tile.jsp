<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:choose>
    <c:when test="${tileInfo.hasBase && tileInfo.enemyTerritory}">
        <jsp:include page="enemybaseview.jsp"></jsp:include>
    </c:when>
    <c:when test="${tileInfo.hasBase && !tileInfo.enemyTerritory}">
        <jsp:include page="ownbaseview.jsp"></jsp:include>
    </c:when>
    <c:otherwise>
        <jsp:include page="tilebaseview.jsp"></jsp:include>
    </c:otherwise>
</c:choose>
<a href="sendtroops.html?x=${tileInfo.x}&${tileInfo.y}">Send Troops</a>