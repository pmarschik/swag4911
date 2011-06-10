<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/resources" var="resourcePath"/>

<c:if test="${troops != null && fn:length(troops)>0 }">
    <h2>Available Troops:</h2>
    <table>
        <c:forEach items="${troops}" var="troop">
            <tr>
                <td>${troop.name}</td>
                <td>
                    <table width="60" border="0">
                        <tr>
                            <td><img src="${resourcePath}<spring:theme code='theme.image.misc.wood'/>" alt="Wood:"
                                     title="Wood" height="30"></td>
                            <td>${troop.costs.amount_wood}</td>

                            <td><img src="${resourcePath}<spring:theme code='theme.image.misc.stone'/>" alt="Stone:"
                                     title="Stone" height="30"></td>
                            <td>${troop.costs.amount_stone}</td>

                            <td><img src="${resourcePath}<spring:theme code='theme.image.misc.crops'/>" alt="Crops::"
                                     title="Crops" height="30"></td>
                            <td>${troop.costs.amount_crops}</td>

                            <td><img src="${resourcePath}<spring:theme code='theme.image.misc.gold'/>" alt="Gold:"
                                     title="Gold" height="30"></td>
                            <td>${troop.costs.amount_gold}</td>
                        </tr>
                    </table>
                </td>
                <td><a class="contentLink"
                       href="train.html?troopTypeId=${building.id}&baseId=${baseId}"><img
                        src="${resourcePath}<spring:theme code='theme.image.troops.train'/>" alt="Build"></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>