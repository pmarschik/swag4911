<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/resources" var="resourcesUrl"/>

<h2>Troop Movements</h2>
<c:choose>
    <c:when test="${troopActions != null && fn:length(troopActions)>0 }">
        <table border="1">
            <tr>
                <td>Destination</td>
                <td>Started</td>
                <td>End Date</td>
                <td>Cancel</td>
            </tr>
            <c:forEach items="${troopActions}" var="action">
                <tr>
                    <td>${action.destination_x},${action.destination_y} </td>
                    <td>${action.startDate}</td>
                    <td>${action.endDate}</td>
                    <c:choose>
                        <c:when test="${action.isAbortable}">
                            <td><a class="contentLink" href="canceltroopaction.html?id=${action.id}"><img
                                    src="${resourcesUrl}<spring:theme code='theme.image.misc.cancel'/>"
                                    alt="Cancel" height="30"> </a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>You have currently no active troop movements</c:otherwise>
</c:choose>

<h2>Troops in Upgrade Process</h2>
<c:choose>
    <c:when test="${troopUpgradeActions != null && fn:length(troopUpgradeActions)>0 }">
        <table border="1">
            <tr>
                <td>Building</td>
                <td>Base</td>
                <td>Level</td>
                <td>End Date</td>
                <td>Cancel</td>
            </tr>
            <c:forEach items="${troopUpgradeActions}" var="action">
                <tr>
                    <td>${action.troopName}</td>
                    <td>${action.destination_x},${action.destination_y} </td>
                    <td>${action.level}</td>
                    <td>${action.endDate}</td>
                    <c:choose>
                        <c:when test="${action.isAbortable}">
                            <td><a class="contentLink" href="canceltroopupgradeaction.html?id=${action.id}"><img
                                    src="${resourcesUrl}<spring:theme code='theme.image.misc.cancel'/>"
                                    alt="Cancel" height="30"> </a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>You have currently no troop upgrades in progress</c:otherwise>
</c:choose>

<h2>Building Actions</h2>
<c:choose>
    <c:when test="${baseActions != null && fn:length(baseActions)>0 }">
        <table border="1">
            <tr>
                <td>Building</td>
                <td>Base</td>
                <td>Square</td>
                <td>Level</td>
                <td>End Date</td>
                <td>Cancel</td>
            </tr>
            <c:forEach items="${baseActions}" var="action">
                <tr>
                    <td>${action.buildingName}</td>
                    <td>${action.destination_x},${action.destination_y} </td>
                    <td>${action.squareId}</td>
                    <td>${action.level}</td>
                    <td>${action.endDate}</td>
                    <c:choose>
                        <c:when test="${action.isAbortable}">
                            <td><a class="contentLink" href="cancelbuildaction.html?id=${action.id}"><img
                                    src="${resourcesUrl}<spring:theme code='theme.image.misc.cancel'/>"
                                    alt="Cancel" height="30"> </a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>You have currently no buildings in progress</c:otherwise>
</c:choose>


<h2>Troops in Training</h2>
<c:choose>
    <c:when test="${troopBuildActions != null && fn:length(troopBuildActions)>0 }">
        <table border="1">
            <tr>
                <td>Name</td>
                <td>Location</td>
                <td>Amount</td>
                <td>End Date</td>
                <td>Cancel</td>
            </tr>
            <c:forEach items="${troopBuildActions}" var="action">
                <tr>
                    <td>${action.troopName}</td>
                    <td>${action.destination_x},${action.destination_y} </td>
                    <td>${action.amount}</td>
                    <td>${action.endDate}</td>
                    <c:choose>
                        <c:when test="${action.isAbortable}">
                            <td><a class="contentLink" href="canceltroopbuildaction.html?id=${action.id}"><img
                                    src="${resourcesUrl}<spring:theme code='theme.image.misc.cancel'/>"
                                    alt="Cancel" height="30"> </a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>You have currently no troop trainings in progress</c:otherwise>
</c:choose>