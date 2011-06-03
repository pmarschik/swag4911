<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table width="100%" border="0">
	<tr colspan="2">TODO: BILD</tr>
	<tr></tr>
	<tr colspan="2">Coordinates:</tr>
	<tr>
		<td>x:</td><td>${x}</td>
	</tr>
	<tr>
		<td>y:</td><td>${y}</td>
	</tr>
	<tr>
		<td>Special Resource</td><td>${specialResource}</td>
	</tr>
</table>
<c:if test="${!troops.isEmpty}">
	<table width="100%" border="0">
		<tr colspan="4">Troops</tr>
		<tr></tr>
		<tr>
			<td>Type</td><td>Level</td><td>Attack</td><td>Defense</td><td>Owner</td>
		</tr>
		<c:forEach items="${troops}" var="troop">
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