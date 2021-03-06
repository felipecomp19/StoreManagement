<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="tab3">
<div class="box">
	<div class="box-header">
		<ul class="box-toolbar">
			<li><button class="btn btn-sm btn-blue" name="exportReport" ><i class="icon-download-alt"></i>&nbsp;&nbsp;<spring:message code="label.exportToExcel"/></button>
		</ul>
	</div>
	<div class="box-content">
		<div id="dataTables">
			<table cellpadding="0" cellspacing="0" border="0"
				class="dTable responsive">
				<thead>
					<tr>
						<th width="40px;">
							<i class="icon-bar-chart"></i>
						</th>
						<th>
							<div><spring:message code="label.employee" /></div>
						</th>
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
							<td>
								<input class="averageTicketChart" type="checkbox" checked="checked" value="${reportData.employee.id}" onclick="reloadGraph('averageTicketChart')">
							</td>
							<td>${reportData.employee.name}</td>
							<c:forEach var="value"
								items="${reportData.averageTicketMapValues}">
								<td>${value}</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot style="font-weight: bold;">
					<tr>
						<td></td>
						<td><spring:message code="label.total"/>
						<c:forEach var="value" items="${reportVM.evolutionOfIndicatorReportDataTotals.averageTicketMapValues}">
							<td class="">${value}</td>
						</c:forEach>
					</tr>
				</tfoot>
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
				<span class="title"><spring:message code="label.charts" />&nbsp;&nbsp;-&nbsp;&nbsp;<spring:message code="label.averageTicket" /></span>
			</div>
			<div class="box-content padded">
				<div class="sine-chart" id="averageTicketChart" style="height: 350px;">
					<input type="hidden" id="keys" value="${reportVM.evolutionOfIndicatorReportData[0].keys}" />
					<c:forEach var="reportData" items="${reportVM.evolutionOfIndicatorReportData}">
						<div name="employees3" empName="${reportData.employee.name}" empId="${reportData.employee.id}">
							<c:forEach var="entry" items="${reportData.averageTicketMap}">
								<input name="graphData3" type="hidden" key="${entry.key}" value="${entry.value}">
							</c:forEach>
						</div>
					</c:forEach>
				</div>
				<div id="averageTicketChart_legend" style="text-align: center;">
					<!-- js will populate with legend based on the chart -->
				</div>
			</div>
		</div>
	</div>
</div>
</div>