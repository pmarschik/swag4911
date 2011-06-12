<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/resources" var="resourcePath"/>
<table id="resourceTable">
    <tr>
        <th></th>
        <th><img src="${resourcePath}<spring:theme code='theme.image.misc.gold'/>" alt="Gold"/></th>
        <th><img src="${resourcePath}<spring:theme code='theme.image.misc.stone'/>" alt="Stone"/></th>
        <th><img src="${resourcePath}<spring:theme code='theme.image.misc.wood'/>" alt="Wood"/></th>
        <th><img src="${resourcePath}<spring:theme code='theme.image.misc.crops'/>" alt="Crops"/></th>
    </tr>
    <tr>
        <td>Amount:</td>
        <td>${resources.amount.amount_gold}</td>
        <td>${resources.amount.amount_stone}</td>
        <td>${resources.amount.amount_wood}</td>
        <td>${resources.amount.amount_crops}</td>
    </tr>
    <tr>
        <td>Income:</td>
        <td>${resources.income.amount_gold}</td>
        <td>${resources.income.amount_stone}</td>
        <td>${resources.income.amount_wood}</td>
        <td>${resources.income.amount_crops}</td>
    </tr>
        <tr>
        <td>Upkeep:</td>
        <td>${resources.upkeep.amount_gold}</td>
        <td>${resources.upkeep.amount_stone}</td>
        <td>${resources.upkeep.amount_wood}</td>
        <td>${resources.upkeep.amount_crops}</td>
    </tr>
</table>