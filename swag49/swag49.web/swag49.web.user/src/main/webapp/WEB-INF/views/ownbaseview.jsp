<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Squares:</h2>
<table>
    <c:forEach items="${squares}" var="square">
        <tr>
        <c:choose>
	        <c:when test="${square.building != null && square.building.level == 0}">
	        	        <!-- Building under construction -->
            	<td><img src="<spring:theme code="theme.image.buildings.underconstruction"/>" alt="Comming soon: ${square.building.name}" title="Comming soon: ${square.building.name}"/> </td>
	        	<td>${square.building.name} under construction</td>
	        </c:when>
	        
	        <c:when test="${square.building != null && square.building.level > 0}">
	        <!-- existing Building -->
	            <td><img src="<spring:theme code="theme.image.buildings.${square.building.name}"/>" alt="${square.building.name}" title="${square.building.name}"/> </td>
	        	<td>${square.building.name} , Level ${square.building.level}</td>
	        	<td>            		
	        		<jsp:include page="displaycosts.jsp">
            			<jsp:param name="resourceValue" value="${square.building.income}" />
            		</jsp:include>
            	</td>
	        	<td>            		
	        		<jsp:include page="displaycosts.jsp">
            			<jsp:param name="resourceValue" value="${square.building.upkeep}" />
            		</jsp:include>
            	</td>
            	<td>            		
	        		<c:if test="${square.building.canUpgrade}">
	        			<a href = "TODO" ><img src="<spring:theme code="theme.image.buildings.upgrade"/>" alt="Upgrade" > </a>
	        		</c:if>
            	</td>
	        </c:when>
	        
	        <c:otherwise>
	        <!-- Empty Square -->
	        TODO
	        </c:otherwise>
        </c:choose>
        </tr>
    </c:forEach>
</table>

<c:if test="${availableTroops != null && availableTroops.size()>0 }">
	<h2>Train Troops:</h2>
	<table>
    	<c:forEach items="${availableTroops}" var="troopType">
            <tr>
            	<td><img src="<spring:theme code="theme.image.troops.${troopType.name}"/>" alt="${troopType.name}" /> </td>
            	<td>${troopType.name}</td>
            	<td>
            		<jsp:include page="displaycosts.jsp">
            			<jsp:param name="resourceValue" value="${troopType.costs}" />
            		</jsp:include>
            	</td>
            	<td><a href = "TODO" ><img src="<spring:theme code="theme.image.buy"/>" alt="Buy" > </a> </td>
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
