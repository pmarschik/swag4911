<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<spring:url value="/resources" var="resourcePath"/>


<h2>Sent to this position:</h2>
<c:if test="${troopsPerTile.troopsPerTile.values() != null && fn:length(troopsPerTile.troopsPerTile.values())>0 }">
    <div id="accordion">
        <c:forEach items="${troopsPerTile.troopsPerTile.values()}" var="troopsPerTile">
            <h3><a href="#">Tile</a></h3>

            <div>
                <c:forEach items="${troopsPerTile}" var="troop">
                    <tr>
                        <td>${troop.type.name}</td>
                        <td>
                            <jsp:include page="displaycosts.jsp">
                                <jsp:param name="resourceValue" value="${troop.costs}"/>
                            </jsp:include>
                        </td>
                        <td><a href="TODO">Send</a></td>
                    </tr>
                </c:forEach>

            </div>
        </c:forEach>
    </div>
</c:if>