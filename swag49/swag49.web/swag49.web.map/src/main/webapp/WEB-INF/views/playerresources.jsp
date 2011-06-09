<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/resources" var="resourcePath"/>
	<table id="resourceTable">
	<tr>
		<th><img src="${resourcePath}/images/misc/gold.jpg" alt="Gold" /></th>
        <th><img src="${resourcePath}/images/misc/stone.jpg" alt="Stone" /></th>
        <th><img src="${resourcePath}/images/misc/wood.jpg" alt="Wood" /></th>
        <th><img src="${resourcePath}/images/misc/crops.jpg" alt="Crops" /></th>
	</tr>
	<tr>
		<td>${amount_gold}</td>
        <td>${amount_stone}</td>
        <td>${amount_wood}</td>
        <td>${amount_crops}</td>
	</tr>
    <tr>

    </tr>
	</table>