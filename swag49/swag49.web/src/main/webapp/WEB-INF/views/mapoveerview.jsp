<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<body>
	<table>
    	<c:forEach items="${displayedTiles}" var="tileLine">
            <tr>
                <c:forEach items="${tileLine.values}" var="tile">
                	<td>
                	<!-- TODO link should be replaced!!! (Or tiles-framework used) -->
                		<a href="tile/${tile.coordinates}">
                		<c:choose>
                			<c:when test="${tile.specialResource == NONE}">
   								<c:choose>
   									<c:when test="${tile.hasBase && tile.enemyTerritory}">
   										<img src="img/normal/enemy_base.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:when test="${tile.hasBase && !tile.enemyTerritory}">
   										<img src="img/normal/own_base.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:when test="${!tile.hasBase && tile.enemyTerritory}">
   										<img src="img/normal/enemy_troops.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:when test="${!tile.hasBase && !tile.enemyTerritory}">
   										<img src="img/normal/own_troops.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:otherwise>
   										<img src="img/normal/empty.jpg" alt="${tile.info}" title="${tile.info}"/>
   									</c:otherwise>
   								</c:choose>          				
						    </c:when>
						    <c:when test="${tile.specialResource == GOLD}">
   								<c:choose>
   									<c:when test="${tile.hasBase && tile.enemyTerritory}">
   										<img src="img/gold/enemy_base.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:when test="${tile.hasBase && !tile.enemyTerritory}">
   										<img src="img/gold/own_base.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:when test="${!tile.hasBase && tile.enemyTerritory}">
   										<img src="img/gold/enemy_troops.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:when test="${!tile.hasBase && !tile.enemyTerritory}">
   										<img src="img/gold/own_troops.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:otherwise>
   										<img src="img/gold/empty.jpg" alt="${tile.info}" title="${tile.info}"/>
   									</c:otherwise>
   								</c:choose>          				
						    </c:when>
						    <c:when test="${tile.specialResource == WOOD}">
   								<c:choose>
   									<c:when test="${tile.hasBase && tile.enemyTerritory}">
   										<img src="img/wood/enemy_base.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:when test="${tile.hasBase && !tile.enemyTerritory}">
   										<img src="img/wood/own_base.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:when test="${!tile.hasBase && tile.enemyTerritory}">
   										<img src="img/wood/enemy_troops.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:when test="${!tile.hasBase && !tile.enemyTerritory}">
   										<img src="img/wood/own_troops.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:otherwise>
   										<img src="img/wood/empty.jpg" alt="${tile.info}" title="${tile.info}"/>
   									</c:otherwise>
   								</c:choose>          				
						    </c:when>
						    <c:when test="${tile.specialResource == STONE}">
   								<c:choose>
   									<c:when test="${tile.hasBase && tile.enemyTerritory}">
   										<img src="img/stone/enemy_base.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:when test="${tile.hasBase && !tile.enemyTerritory}">
   										<img src="img/stone/own_base.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:when test="${!tile.hasBase && tile.enemyTerritory}">
   										<img src="img/stone/enemy_troops.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:when test="${!tile.hasBase && !tile.enemyTerritory}">
   										<img src="img/stone/own_troops.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:otherwise>
   										<img src="img/stone/empty.jpg" alt="${tile.info}" title="${tile.info}"/>
   									</c:otherwise>
   								</c:choose>          				
						    </c:when>
						    <c:when test="${tile.specialResource == CROPS}">
   								<c:choose>
   									<c:when test="${tile.hasBase && tile.enemyTerritory}">
   										<img src="img/crops/enemy_base.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:when test="${tile.hasBase && !tile.enemyTerritory}">
   										<img src="img/crops/own_base.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:when test="${!tile.hasBase && tile.enemyTerritory}">
   										<img src="img/crops/enemy_troops.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:when test="${!tile.hasBase && !tile.enemyTerritory}">
   										<img src="img/crops/own_troops.jpg" alt="${tile.info}" title="${tile.info}"/>  								
   									</c:when>
   									<c:otherwise>
   										<img src="img/crops/empty.jpg" alt="${tile.info}" title="${tile.info}"/>
   									</c:otherwise>
   								</c:choose>          				
						    </c:when>
						    <c:otherwise>
						        <img src="img/unknown.jpg" alt="unknown" title="unknown"/>
						    </c:otherwise>
						</c:choose>
                		</a>
                	</td>
                </c:forEach>
            </tr>
        </c:forEach>
	</table>
</body>
