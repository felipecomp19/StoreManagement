<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="tab2">
<div id="dataTables">
	<table cellpadding="0" cellspacing="0" border="0"
		class="dTable responsive">
		<thead>
			<tr>
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


<!-- charts -->
<br/>
<div class="row">
	<div class="col-md-12">
		<div class="box">
			<div class="box-header">
				<span class="title"><spring:message code="label.charts" />&nbsp;&nbsp;-&nbsp;&nbsp;<spring:message code="label.averageValueOfTheProduct" /></span>
			</div>
			<div class="box-content padded">
				<div class="sine-chart" id="averageValueOfTheProductChart" style="height: 350px;">
					<input type="hidden" id="keys" value="${reportVM.evolutionOfIndicatorReportData[0].keys}" />
					<c:forEach var="reportData" items="${reportVM.evolutionOfIndicatorReportData}">
						<div name="employees2" empName="${reportData.employee.name}">
							<c:forEach var="entry" items="${reportData.averageValueOfTheProductMap}">
								<input name="graphData2" type="hidden" key="${entry.key}" value="${entry.value}">
							</c:forEach>
						</div>
					</c:forEach>
				</div>
				<div id="averageValueOfTheProductChart_legend" style="text-align: center;">
					<!-- js will populate with legend based on the chart -->
				</div>
			</div>
		</div>
	</div>
</div>
</div>

