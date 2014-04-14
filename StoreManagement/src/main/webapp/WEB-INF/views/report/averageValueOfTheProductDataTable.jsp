<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="dataTables">
	<table cellpadding="0" cellspacing="0" border="0"
		class="dTable responsive">
		<thead>
			<tr>
				<th><div>
						<spring:message code="label.store" />
					</div></th>
				<th><div>
						<spring:message code="label.employee" />
					</div></th>
				<c:forEach var="date"
					items="${reportVM.evolutionOfIndicatorReportData[0].keys}">
					<th><div>${date}</div></th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="reportData"
				items="${reportVM.evolutionOfIndicatorReportData}">
				<tr>
					<td>${reportData.employee.store.name}</td>
					<td>${reportData.employee.name}</td>
					<c:forEach var="value"
						items="${reportData.averageValueOfTheProductMapValues}">
						<td>${value}</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>