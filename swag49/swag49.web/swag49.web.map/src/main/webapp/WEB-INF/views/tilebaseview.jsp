<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<table width="100%" border="0">
	<tr ><td colspan="2">TODO: BILD</td></tr>
	<tr></tr>
	<tr><td  colspan="2">Coordinates:</td></tr>
	<tr>
		<td>x:</td><td>${tileInfo.x}</td>
	</tr>
	<tr>
		<td>y:</td><td>${tileInfo.y}</td>
	</tr>
	<tr>
		<td>Special Resource</td><td>${tileInfo.specialResource}</td>
	</tr>
</table>
<c:if test="${tileInfo.hasTroops}">
	<table width="100%" border="0">
		<tr colspan="4">Troops</tr>
		<tr></tr>
		<tr>
			<td>Type</td><td>Level</td><td>Attack</td><td>Defense</td><td>Owner</td>
		</tr>
		<c:forEach items="${tileInfo.troops}" var="troop">
            <tr>
                <td>${troop.type}</td>
                <td>${troop.level} </td>
                <td>${troop.attack}</td>
                <td>${troop.defense}</td>
                <td>${troop.owner}</td>
            </tr>
        </c:forEach>
	</table>
</c:if>