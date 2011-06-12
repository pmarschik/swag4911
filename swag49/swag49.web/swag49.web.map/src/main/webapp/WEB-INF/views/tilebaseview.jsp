<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<table border="0">
    <tr></tr>
    <tr>
        <td colspan="2">Coordinates:</td>
    </tr>
    <tr>
        <td>x:</td>
        <td>${tileInfo.x}</td>
    </tr>
    <tr>
        <td>y:</td>
        <td>${tileInfo.y}</td>
    </tr>
    <tr>
        <td>Special Resource</td>
        <td>${tileInfo.specialResource}</td>
    </tr>
</table>
<c:if test="${tileInfo.enemyTerritory && tileInfo.hasTroops}">

</c:if>
<c:if test="${tileInfo.hasTroops}">
    <c:if test="${tileInfo.enemyTerritory }">
        <h2>Enemy Troops</h2>
    </c:if>
    <c:if test="${!tileInfo.enemyTerritory }">
        <h2>Your troops</h2>
    </c:if>
    <table width="100%" border="0">
        <tr>
            <td>Type</td>
            <td>Level</td>
            <td>Attack</td>
            <td>Defense</td>
            <td>Owner</td>
        </tr>
        <c:forEach items="${tileInfo.troops}" var="troop">
            <tr>
                <td>${troop.name}</td>
                <td>${troop.level} </td>
                <td>${troop.strength}</td>
                <td>${troop.defense}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>