<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<spring:url value="/resources" var="resourcePath"/>


<h2>Send to this position:</h2>
<c:if test="${troopsPerTile.tileList != null && fn:length(troopsPerTile.tileList)>0 }">
    <form:form method="post" action="./sendTroops" commandName="troopsPerTile">
        <form:hidden path="x"/>
        <form:hidden path="y"/>
        <div id="accordion">

            <c:forEach items="${troopsPerTile.tileList}" var="tile" varStatus="tileIndex">

                <h3><a href="#">Tile</a></h3>

                <div>
                    <form:hidden path="tileList[${tileIndex.index}].x"/>
                    <form:hidden path="tileList[${tileIndex.index}].y"/>
                    <form:hidden path="tileList[${tileIndex.index}].mapId"/>
                    <c:forEach items="${tile.troops}" var="troop" varStatus="troopIndex">

                        <tr>
                            <td>${troop.id}
                                <form:hidden path="tileList[${tileIndex.index}].troops[${troopIndex.index}].id"/>

                            </td>
                            <td>${troop.name}</td>
                            <form:hidden path="tileList[${tileIndex.index}].troops[${troopIndex.index}].name"/>
                            <td>
                                <form:checkbox path="tileList[${tileIndex.index}].troops[${troopIndex.index}].sendMe"/>
                            </td>
                        </tr>
                    </c:forEach>

                </div>
            </c:forEach>
        </div>
        <input class="button" type="submit" value="Send"/>
    </form:form>
</c:if>
<script type="text/javascript">
    $(function() {
        $("#accordion").accordion();
    });
</script>