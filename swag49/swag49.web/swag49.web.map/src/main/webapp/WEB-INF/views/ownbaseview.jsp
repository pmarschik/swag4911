<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/resources" var="resourcePath"/>
 <div id="actionMessage">
     <c:if test="${message != null}">
        ${message}
     </c:if>
 </div>
<h2>Troops:</h2>
<table>
    <tr>
        <td><a class="contentLink" href="troopoverview.html?baseId=${tileInfo.baseId}"><img
                src="${resourcePath}<spring:theme code='theme.image.misc.troops'/>" alt="watch troops" height="50"></a>
        </td>
        <td>Present Troops</td>
    </tr>
    <tr>
        <td><a class="contentLink" href="traintroops.html?baseId=${tileInfo.baseId}"><img
                src="${resourcePath}<spring:theme code='theme.image.misc.train_troops'/>" alt="train new troops"
                height="50"></a></td>
        <td>Train New Troops</td>
    </tr>
</table>


<h2>Squares:</h2>
<table border="1">
    <tr>
        <td>Name</td>
        <td>Level</td>
        <td>Resource Production</td>
        <td>Upkeep Costs</td>
        <td>Upgrade</td>
    </tr>

    <%--@elvariable id="tileInfo" type="swag49.web.model.TileOverviewDTOFull"--%>
    <c:forEach items="${tileInfo.squares}" var="square">
        <tr>
            <c:choose>
                <c:when test="${square.building != null && square.building.level == 0}">
                    <!-- Building under construction -->
                    <td colspan="5">${square.building.name} under construction</td>
                </c:when>

                <c:when test="${square.building != null && square.building.level > 0}">
                    <!-- existing Building -->
                    <td>${square.building.name}</td>
                    <td>${square.building.level}</td>
                    <td>
                        <table width="60" border="0">
                            <tr>
                                <td><img src="${resourcePath}<spring:theme code='theme.image.misc.wood'/>" alt="Wood:"
                                         title="Wood" height="30"></td>
                                <td>${square.building.resourceProduction.amount_wood}</td>

                                <td><img src="${resourcePath}<spring:theme code='theme.image.misc.stone'/>" alt="Stone:"
                                         title="Stone" height="30"></td>
                                <td>${square.building.resourceProduction.amount_stone}</td>

                                <td><img src="${resourcePath}<spring:theme code='theme.image.misc.crops'/>"
                                         alt="Crops::" title="Crops" height="30"></td>
                                <td>${square.building.resourceProduction.amount_crops}</td>

                                <td><img src="${resourcePath}<spring:theme code='theme.image.misc.gold'/>" alt="Gold:"
                                         title="Gold" height="30"></td>
                                <td>${square.building.resourceProduction.amount_gold}</td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <table width="60" border="0">
                            <tr>
                                <td><img src="${resourcePath}<spring:theme code='theme.image.misc.wood'/>" alt="Wood:"
                                         title="Wood" height="30"></td>
                                <td>${square.building.upkeepCosts.amount_wood}</td>

                                <td><img src="${resourcePath}<spring:theme code='theme.image.misc.stone'/>" alt="Stone:"
                                         title="Stone" height="30"></td>
                                <td>${square.building.upkeepCosts.amount_stone}</td>

                                <td><img src="${resourcePath}<spring:theme code='theme.image.misc.crops'/>"
                                         alt="Crops::" title="Crops" height="30"></td>
                                <td>${square.building.upkeepCosts.amount_crops}</td>

                                <td><img src="${resourcePath}<spring:theme code='theme.image.misc.gold'/>" alt="Gold:"
                                         title="Gold" height="30"></td>
                                <td>${square.building.upkeepCosts.amount_gold}</td>
                            </tr>
                        </table>
                    </td>
                    <td align="center">
                        <c:choose>
                            <c:when test="${square.building.canUpgrade && square.building.upgradeCosts != null}">
                                <a class="contentLink" href="buildingupgrade.html?baseId=${square.baseId}&position=${square.position}&x=${x}&y=${y}"><img
                                        src="${resourcePath}<spring:theme code='theme.image.misc.upgrade'/>"
                                        alt="Upgrade"> </a>
                            </c:when>
                            <c:when test="${square.building.canUpgrade == false && square.building.upgradeCosts != null}">
                                Upgrade in progress...
                            </c:when>
                            <c:otherwise>
                                maximal level reached!!!
                            </c:otherwise>
                        </c:choose>

                    </td>
                </c:when>

                <c:otherwise>
                    <!-- Empty Square -->
                    <td colspan="2">Empty Square</td>
                    <td colspan="3"><a class="contentLink"
                                       href="build.html?baseId=${square.baseId}&position=${square.position}"><img
                            src="${resourcePath}<spring:theme code='theme.image.buildings.build'/>" alt="Build"></a>
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
