<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/resources" var="resourcePath"/>

<c:if test="${troops != null && fn:length(troops)>0 }">
<h2>Troops stationed at Base with ID ${baseId}</h2>
    <table border="1">
         <tr>
             <td>Type</td><td>Level</td> <td>Attributes</td>   <td colspan="2">Upgrade Costs</td>
             </tr>
        <c:forEach items="${troops}" var="troop">
            <tr>
                <td>${troop.name}</td>
                <td>${troop.level}</td>
                <td>
                    <table>
                        <tr>
                            <td>Strength:</td>
                            <td>${troop.strength}</td>
                        </tr>
                        <tr>
                            <td>Defense:</td>
                            <td>${troop.defense}</td>
                        </tr>
                        <tr>
                            <td>Speed:</td>
                            <td>${troop.speed}</td>
                        </tr>
                        <tr>
                            <td>Stealing Capacity:</td>
                            <td>${troop.cargo_capacity}</td>
                        </tr>
                    </table>
                </td>
                <c:choose>
                    <c:when test="${troop.canUpgrade}">
                        <td>
                            <table width="60" border="0">
                                <tr>
                                    <td><img src="${resourcePath}<spring:theme code='theme.image.misc.wood'/>"
                                             alt="Wood:"
                                             title="Wood" height="30"></td>
                                    <td>${troop.upgradeCost.amount_wood}</td>

                                    <td><img src="${resourcePath}<spring:theme code='theme.image.misc.stone'/>"
                                             alt="Stone:"
                                             title="Stone" height="30"></td>
                                    <td>${troop.upgradeCost.amount_stone}</td>

                                    <td><img src="${resourcePath}<spring:theme code='theme.image.misc.crops'/>"
                                             alt="Crops::" title="Crops" height="30"></td>
                                    <td>${troop.upgradeCost.amount_crops}</td>

                                    <td><img src="${resourcePath}<spring:theme code='theme.image.misc.gold'/>"
                                             alt="Gold:"
                                             title="Gold" height="30"></td>
                                    <td>${troop.upgradeCost.amount_gold}</td>
                                </tr>
                            </table>
                        </td>
                        <td><a href="troopupgrade.html?troopId=${troop.id}"><img
                                src="${resourcePath}<spring:theme code='theme.image.misc.upgrade'/>"
                                alt="Upgrade"></a>
                        </td>
                    </c:when>
                    <c:when test="${troop.canUpgrade == false}">
                        <td>
                        </td>
                        <td>
                        </td>
                    </c:when>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${troops == null || fn:length(troops)==0 }">
    Sorry, but you have no troops here!
    <a href="tile/?x=${back_x}&y=${back_y}">back</a>
</c:if>