<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<spring:url value="/resources" var="resourcePath"/>
<html>
<head>
	<title>Map View</title>
    <LINK href="../../resources/css/style.css" rel="stylesheet" type="text/css">
    <!--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> -->
    <script type="text/javascript" src="${resourcePath}/js/jquery-1.5.1.min.js"></script>
    <script type="text/javascript" src="${resourcePath}/js/jquery-ui-1.8.13.custom.min.js"></script>
</head>
<body>
	<div id="header">
        <div id="playerResources">

	<table id="resourceTable">
	<tr>
		<th><img src="${resourcePath}/images/misc/gold.jpg" alt="Gold" /></th>
        <th><img src="${resourcePath}/images/misc/stone.jpg" alt="Stone" /></th>
        <th><img src="${resourcePath}/images/misc/wood.jpg" alt="Wood" /></th>
        <th><img src="${resourcePath}/images/misc/crops.jpg" alt="Crops" /></th>
	</tr>
	<tr>
		<td>${resources.amount_gold}</td>
        <td>${resources.amount_stone}</td>
        <td>${resources.amount_wood}</td>
        <td>${resources.amount_crops}</td>
	</tr>
    <tr>

    </tr>
	</table>
        </div>
        <a id="resourceRefresh" href="playerresources.html">Refresh</a>
	</div>
	<div id="subContent">
		<div id="menu">Menu:<br />
		<ul>
			<li><a class="contentLink" href="mapoverview.html">Map Overview</a></li>
			<li><a id="messagingLink" class="contentLink" href="messaging.html">Messaging</a></li>
            <li><a class="contentLink" href="actions.html">Actions</a></li>
			<li><a class="contentLink" href="../statistics/">Statistics</a></li>
		</ul>

		</div>
		<div id="content"><spring:url value="/resources" var="resourcePath"/>
<table>
    <tr>
        <td class="arrowUpCell" colspan="3">
            <a class="contentLink" href="./mapoverview.html?xLow=${xLow}&yLow=${yLow - 1}&xHigh=${xHigh}&yHigh=${yHigh - 1}">
                <img class="arrowPic" src="${resourcePath}<spring:theme code='theme.image.misc.up'/>" alt="Up" title="UP"/>
            </a>
        </td>
    </tr>

    <tr>
        <td>
            <a class="contentLink" href="./mapoverview.html?xLow=${xLow - 1}&yLow=${yLow}&xHigh=${xHigh - 1}&yHigh=${yHigh}">
                <img class="arrowPic"src="${resourcePath}<spring:theme code='theme.image.misc.left'/>"
                     alt="Left" title="Left"/>
            </a>
        </td>
        <td>
            <table>
                <c:forEach items="${tiles}" var="tileLine">
                    <tr>
                        <c:forEach items="${tileLine}" var="tile">
                            <td class="tilePic">
                                <!-- TODO link should be replaced!!! (Or tiles-framework used) -->
                                <a class="contentLink" href="tile/?x=${tile.x}&y=${tile.y}">
                                    <c:choose>
                                        <c:when test="${tile.specialResource == 'NONE'}">
                                            <c:choose>
                                                <c:when test="${tile.hasBase && tile.enemyTerritory}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.normal.enemy_base'/>"
                                                         alt="${tile.info}" title="${tile.info}"/>
                                                </c:when>
                                                <c:when test="${tile.hasBase && !tile.enemyTerritory}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.normal.own_base'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:when test="${!tile.hasBase && tile.enemyTerritory && tile.hasTroops}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.normal.enemy_troops'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:when test="${!tile.hasBase && !tile.enemyTerritory && tile.hasTroops}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.normal.own_troops'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <img    src="${resourcePath}<spring:theme code='theme.image.tile.normal.empty'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:when test="${tile.specialResource == 'GOLD'}">
                                            <c:choose>
                                                <c:when test="${tile.hasBase && tile.enemyTerritory}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.gold.enemy_base'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:when test="${tile.hasBase && !tile.enemyTerritory}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.gold.own_base'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:when test="${!tile.hasBase && tile.enemyTerritory && tile.hasTroops}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.gold.enemy_troops'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:when test="${!tile.hasBase && !tile.enemyTerritory && tile.hasTroops}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.gold.own_troops'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:otherwise>
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.misc.gold'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:when test="${tile.specialResource == 'WOOD'}">
                                            <c:choose>
                                                <c:when test="${tile.hasBase && tile.enemyTerritory}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.wood.enemy_base'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:when test="${tile.hasBase && !tile.enemyTerritory}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.wood.own_base'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:when test="${!tile.hasBase && tile.enemyTerritory && tile.hasTroops}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.wood.enemy_troops'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:when test="${!tile.hasBase && !tile.enemyTerritory && tile.hasTroops}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.wood.own_troops'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:otherwise>
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.misc.wood'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:when test="${tile.specialResource == 'STONE'}">
                                            <c:choose>
                                                <c:when test="${tile.hasBase && tile.enemyTerritory}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.stone.enemy_base'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:when test="${tile.hasBase && !tile.enemyTerritory}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.stone.own_base'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:when test="${!tile.hasBase && tile.enemyTerritory && tile.hasTroops}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.stone.enemy_troops'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:when test="${!tile.hasBase && !tile.enemyTerritory && tile.hasTroops}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.stone.own_troops'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:otherwise>
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.misc.stone'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:when test="${tile.specialResource == 'CROPS'}">
                                            <c:choose>
                                                <c:when test="${tile.hasBase && tile.enemyTerritory}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.crops.enemy_base'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:when test="${tile.hasBase && !tile.enemyTerritory}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.crops.own_base'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:when test="${!tile.hasBase && tile.enemyTerritory && tile.hasTroops}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.crops.enemy_troops'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:when test="${!tile.hasBase && !tile.enemyTerritory && tile.hasTroops}">
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.tile.crops.own_troops'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:when>
                                                <c:otherwise>
                                                     <img    src="${resourcePath}<spring:theme code='theme.image.misc.crops'/>"
                                                         alt="${tile.info}"
                                                         title="${tile.info}"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                             <img    src="${resourcePath}<spring:theme code='theme.image.tile.normal.unknown'/>"
                                                 alt="unknown" title="unknown"/>
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>

        </td>
        <td>
            <a class="contentLink" href="./mapoverview.html?xLow=${xLow+1}&yLow=${yLow}&xHigh=${xHigh+1}&yHigh=${yHigh}">
                 <img class="arrowPic" src="${resourcePath}<spring:theme code='theme.image.misc.right'/>"
                     alt="Right" title="Right"/>
            </a>
        </td>
    </tr>

    <tr>
        <td class="arrowUpCell" colspan="3">
            <a class="contentLink" href="./mapoverview.html?xLow=${xLow}&yLow=${yLow + 1}&xHigh=${xHigh}&yHigh=${yHigh + 1}"><img class="arrowPic"
                    src="${resourcePath}<spring:theme code='theme.image.misc.down'/>" alt="Down" title="Down"/>
            </a>
        </td>
    </tr>
</table>
</div>
	</div>
<script type="text/javascript">
    $(".contentLink").live("click", function () {
        $.get($(this).attr("href"),function (result) {
            $("#content").html(result);
        });

        return false;
    });
</script>
<script type="text/javascript">
    $("#resourceRefresh").live("click", function () {
        $.get($(this).attr("href"),function (result) {
            $("#playerResources").html(result);
        });

        return false;
    });
</script>
</body>
</html>
