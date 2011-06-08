<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/resources" var="resourcePath"/>
	<table id="resourceTable">
	<tr>
		<th><img src="${resourcePath}/gold.jpg" alt="Gold" /></th>
        <th><img src="${resourcePath}/stone.jpg" alt="Stone" /></th>
        <th><img src="${resourcePath}/wood.jpg" alt="Wood" /></th>
        <th><img src="${resourcePath}/crops.jpg" alt="Crops" /></th>
	</tr>
	<tr>
		<td>${tile.getAmount_gold()}</td>
        <td>${tile.getAmount_stone()}</td>
        <td>${tile.getAmount_wood()}</td>
        <td>${tile.getAmount_crops()}</td>
	</tr>
	</table>