<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container padded">
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
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header">
					<span class="title"><spring:message code="label.charts" /></span>
				</div>
				<div class="box-content padded">
					<div class="row">
						<form:form class="form-horizontal fill-up" modelAttribute="reportVM" action="#" >
							<div class="col-md-4 form-group">
								<label class="control-label col-md-2"><spring:message code="label.indicators" /></label>
								<div class="col-md-10">
								</div>
							</div>
							<div class="col-md-4 form-group">
								<label class="control-label col-md-2"><spring:message code="label.stores" /></label>
								<div class="col-md-10">
									<form:select id="storeSL" path="store" items="${storeList}" itemLabel="nameWithDesc" itemValue="id" class="uniform"/>
								</div>
							</div>
						</form:form>
					</div>
					<!-- <div id="indChart" class="xcharts-indChart" style="width: 100%; height: 300px" > -->
						<c:forEach var="entry" items="${reportData.achievementOfGoalsMap}">
							Map key: ${entry.key}<br/>
    						Map value: ${entry.value}<br/>
						</c:forEach>
					<!-- </div> -->
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
	});
</script>