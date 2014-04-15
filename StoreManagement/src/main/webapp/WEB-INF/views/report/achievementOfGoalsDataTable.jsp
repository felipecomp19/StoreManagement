<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="tab1">
<div class="row">
	<div class="col-md-12">
		<div id="dataTables">
			<table cellpadding="0" cellspacing="0" border="0" class="dTable responsive">
				<thead>
					<tr>
						<th>
							<div><spring:message code="label.store" /></div>
						</th>
						<th>
							<div><spring:message code="label.employee" /></div>
						</th>
						<c:forEach var="date" items="${reportVM.evolutionOfIndicatorReportData[0].keys}">
							<th class="header" text="${date}"><div>${date}</div></th>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="reportData" items="${reportVM.evolutionOfIndicatorReportData}">
						<tr>
							<td>${reportData.employee.store.name}</td>
							<td>${reportData.employee.name}</td>
							<c:forEach var="value" items="${reportData.achievementOfGoalsMapValues}">
								<td class="">${value}</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<!-- charts -->
<br/>
<div class="row">
	<div class="col-md-12">
		<div class="box">
			<div class="box-header">
				<span class="title"><spring:message code="label.charts" /></span>
			</div>
			<div class="box-content padded">
				<div class="sine-chart" id="achievementOfGoalChart">
					<input type="hidden" id="keys" value="${reportVM.evolutionOfIndicatorReportData[0].keys}" />
					<c:forEach var="reportData" items="${reportVM.evolutionOfIndicatorReportData}">
						<div name="employees" empName="${reportData.employee.name}">
							<c:forEach var="entry" items="${reportData.achievementOfGoalsMap}">
								<input name="graphData" type="hidden" key="${entry.key}" value="${entry.value}">
							</c:forEach>
						</div>
					</c:forEach>
				</div>
				<div id="achievementOfGoalChart_legend" style="text-align: center;">
					<!-- js will populate with legend based on the chart -->
				</div>
			</div>
		</div>
	</div>
</div>
</div>

