<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/resources" var="resourcePath"/>

<h2>Troops:</h2>
<a class="contentLink" href="troopoverview.html?baseId=${tileInfo.baseId}"><img src="${resourcePath}<spring:theme code='theme.image.misc.troops'/>" alt="Troops" ></a>
<h2>Squares:</h2>
<table>
    <c:forEach items="${tileInfo.squares}" var="square">
        <tr>
        <c:choose>
	        <c:when test="${square.building != null && square.building.isOfLevel.id.level == 0}">
	        	        <!-- Building under construction -->
	        	<td>${square.building.type.name} under construction</td>
	        </c:when>
	        
	        <c:when test="${square.building != null && square.building.isOfLevel.id.level > 0}">
	        <!-- existing Building -->
	        	<td>${square.building.type.name} , Level ${square.building.isOfLevel.id.level}</td>
	        	<td>            		
<table width="60" border="0">
	<tr>
		<td> <img src="${resourcePath}<spring:theme code='theme.image.misc.wood'/>" alt="Wood:" title="Wood" height="30"> </td>
		<td>${square.building.income.amount_wood}</td>

		<td><img src="${resourcePath}<spring:theme code='theme.image.misc.stone'/>" alt="Stone:" title="Stone" height="30"></td>
		<td>${square.building.income.amount_stone}</td>

		<td><img src="${resourcePath}<spring:theme code='theme.image.misc.crops'/>" alt="Crops::" title="Crops" height="30"></td>
		<td>${square.building.income.amount_crops}</td>

		<td><img src="${resourcePath}<spring:theme code='theme.image.misc.gold'/>" alt="Gold:" title="Gold" height="30"></td>
		<td>${square.building.income.amount_gold}</td>
	</tr>
</table>
            	</td>
	        	<td>            		
<table width="60" border="0">
	<tr>
		<td> <img src="${resourcePath}<spring:theme code='theme.image.misc.wood'/>" alt="Wood:" title="Wood" height="30"> </td>
		<td>${square.building.upkeep.amount_wood}</td>

		<td><img src="${resourcePath}<spring:theme code='theme.image.misc.stone'/>" alt="Stone:" title="Stone" height="30"></td>
		<td>${square.building.upkeep.amount_stone}</td>

		<td><img src="${resourcePath}<spring:theme code='theme.image.misc.crops'/>" alt="Crops::" title="Crops" height="30"></td>
		<td>${square.building.upkeep.amount_crops}</td>

		<td><img src="${resourcePath}<spring:theme code='theme.image.misc.gold'/>" alt="Gold:" title="Gold" height="30"></td>
		<td>${square.building.upkeep.amount_gold}</td>
	</tr>
</table>
            	</td>
            	<td>            		
	        		<c:if test="${square.building.canUpgrade}">
	        			<a href = "buildingupgrade.html" ><img src="<spring:theme code='theme.image.misc.upgrade'/>" alt="Upgrade" > </a>
	        		</c:if>
            	</td>
	        </c:when>
	        
	        <c:otherwise>
	        <!-- Empty Square -->
	        <td colspan="2">Empty Square</td>
                <td><a class="contentLink" href="build.html?baseId=${square.id.baseId}&position=${square.id.position}"><img src="${resourcePath}<spring:theme code='theme.image.buildings.build'/>" alt="Build" ></a></td>
	        </c:otherwise>
        </c:choose>
        </tr>
    </c:forEach>
</table>

<c:if test="${availableTroops != null && fn:length(availableTroops)>0 }">
	<h2>Train Troops:</h2>
	<table>
    	<c:forEach items="${availableTroops}" var="troopType">
            <tr>
            	<td><img src="<spring:theme code='theme.image.troops.${troopType.name}'/>" alt="${troopType.name}" /> </td>
            	<td>${troopType.name}</td>
            	<td>
            		<jsp:include page="displaycosts.jsp">
            			<jsp:param name="resourceValue" value="${troopType.costs}" />
            		</jsp:include>
            	</td>
            	<td><a href = "TODO" ><img src="<spring:theme code='theme.image.buy'/>" alt="Buy" > </a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<!--  <select>
  <option>Small
  <option>Medium
  <option>Large
  <option>X-Large
</select>
-->
