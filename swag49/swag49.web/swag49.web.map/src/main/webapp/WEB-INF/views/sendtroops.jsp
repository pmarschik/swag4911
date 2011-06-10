<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<spring:url value="/resources" var="resourcePath"/>


<h2>Sent to this position:</h2>
<c:if test="${tileInfo.availableTroops != null && fn:length(tileInfo.availableTroops)>0 }">
    <div id="accordion">
        <c:forEach items="${availableTroops}" var="troopsPerTile">
            <h3><a href="#">Tile</a></h3>

            <div>
                <c:forEach items="${troopsPerTile}" var="troop">
                    <tr>
                        <td><img src="<spring:theme code='theme.image.troops.${troop.name}'/>"
                                 alt="${troop.name}"/></td>
                        <td>${troop.name}</td>
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