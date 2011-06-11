<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Troop Movements</h2>
<table>
    <tr>
        <td>Destination</td>
        <td>Started</td>
        <td>End Date</td>
    </tr>
    <c:forEach items="${movementActions}" var="action">
        <tr>
            <td>${action.destination.x},${action.destination.y} </td>
            <td>${action.startDate}</td>
            <td>${action.endDate}</td>
            <c:choose>
                <c:when test="${action.isAbortable}">
                    <td><a href="canceltroopaction.html?id=${action.id}"><img
                            src="<spring:theme code='theme.image.misc.cancel'/>"
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

<h2>Troops in Upgrade Process</h2>
<table>
    <tr>
        <td>Building</td>
        <td>Base</td>
        <td>Level</td>
        <td>End Date</td>
    </tr>
    <c:forEach items="${troopUpgradeActions}" var="action">
        <tr>
            <td>${action.troopName}</td>
            <td>${action.base.x},${action.base.y} </td>
            <td>${action.level}</td>
            <td>${action.endDate}</td>
            <c:choose>
                <c:when test="${action.isAbortable}">
                    <td><a href="canceltroopupgradeaction.html?id=${action.id}"><img
                            src="<spring:theme code='theme.image.misc.cancel'/>"
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


<h2>Building Actions</h2>
<table>
    <tr>
        <td>Building</td>
        <td>Base</td>
        <td>Square</td>
        <td>Level</td>
        <td>End Date</td>
    </tr>
    <c:forEach items="${baseActions}" var="action">
        <tr>
            <td>${action.buildingName}</td>
            <td>${action.base.x},${action.base.y} </td>
            <td>${action.squareId}</td>
            <td>${action.level}</td>
            <
            td>${action.endDate}</td>
            <c:choose>
                <c:when test="${action.isAbortable}">
                    <td><a href="cancelbuildaction.html?id=${action.id}"><img
                            src="<spring:theme code='theme.image.misc.cancel'/>"
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