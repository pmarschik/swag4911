<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/resources" var="resourcePath"/>

<c:if test="${buildings != null && fn:length(buildings)>0 }">
	<h2>Buildings available:</h2>
	<table>
    	<c:forEach items="${buildings}" var="building">
            <tr>
            	<td>${building.name}</td>
            	<td>
<table width="60" border="0">
	<tr>
		<td> <img src="${resourcePath}<spring:theme code='theme.image.misc.wood'/>" alt="Wood:" title="Wood" height="30"> </td>
		<td>${building.costs.amount_wood}</td>

		<td><img src="${resourcePath}<spring:theme code='theme.image.misc.stone'/>" alt="Stone:" title="Stone" height="30"></td>
		<td>${building.costs.amount_stone}</td>

		<td><img src="${resourcePath}<spring:theme code='theme.image.misc.crops'/>" alt="Crops::" title="Crops" height="30"></td>
		<td>${building.costs.amount_crops}</td>

		<td><img src="${resourcePath}<spring:theme code='theme.image.misc.gold'/>" alt="Gold:" title="Gold" height="30"></td>
		<td>${building.costs.amount_gold}</td>
	</tr>
</table>
            	</td>
            	<td><a class="contentLink" href="build.html?buildingTypeId=${building.id}&baseId=${baseId}&position=${position}"><img src="${resourcePath}<spring:theme code='theme.image.buildings.build'/>" alt="Build" ></a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>