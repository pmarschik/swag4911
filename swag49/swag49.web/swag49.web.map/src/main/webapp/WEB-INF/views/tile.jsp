<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:url value="/resources" var="resourcePath"/>


<c:choose>
    <c:when test="${tileInfo.hasBase && tileInfo.enemyTerritory}">
        <jsp:include page="enemybaseview.jsp"/>
    </c:when>
    <c:when test="${tileInfo.hasBase && !tileInfo.enemyTerritory}">
        <jsp:include page="ownbaseview.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="tilebaseview.jsp"/>
    </c:otherwise>
</c:choose>
<a class="contentLink" href="sendtroops.html?x=${tileInfo.x}&y=${tileInfo.y}"><img
                src="${resourcePath}<spring:theme code='theme.image.troops.movement'/>" alt="Troop Movement"
                width="200"></a>