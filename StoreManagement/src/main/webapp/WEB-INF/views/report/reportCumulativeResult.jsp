<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<t:template>
	<jsp:body>
		<div class="container">
			<div class="row">
				<div class="area-top clearfix">
					<div class="pull-left header">
						<h3 class="title">
							<i class="icon-book"></i>
							<spring:message code="label.reports" />
						</h3>
						<h5>
							<span><spring:message code="report.cumulativeResult" /></span>
						</h5>
					</div>
				</div>
			</div>
		</div>

		<!-- Breadcrumb -->		
		<div class="container padded">
			<div class="row">
				<div id="breadcrumbs">
					<div class="breadcrumb-button blue">
						<span class="breadcrumb-label">
							<i class="icon-home"></i>Home
						</span>
						<span class="breadcrumb-arrow"><span></span></span>
					</div>
					<div class="breadcrumb-button">
						<span class="breadcrumb-label"> <i class="icon-book"></i>
							<spring:message code="label.reports" />
						</span> <span class="breadcrumb-arrow"><span></span></span>
					</div>
					<div class="breadcrumb-button">
						<span class="breadcrumb-label"> <i class="icon-book"></i>
							<spring:message code="report.cumulativeResult" />
						</span> <span class="breadcrumb-arrow"><span></span></span>
					</div>
				</div>
			</div>
		</div>
		<!-- [END] Breadcrumb -->
		
		<!-- main content -->
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-header">
							<span class="title"><spring:message code="report.cumulativeResult" /></span>
						</div>
						<div class="box-content">
							<div class="row">
								<form:form class="form-horizontal fill-up validatable" method="POST" commandName="reportVM" modelAttribute="reportVM" action="${pageContext.request.contextPath}/report/generate" >
									<form:select id="reportsSL" path="selectedReport">
										<form:option value="2"><spring:message code="report.cumulativeResult"/></form:option>
									</form:select>
									<div class="padded col-md-12" >
										<div class="form-group">
											<label class="control-label col-md-1"><spring:message code="label.store"/></label>
											<div class="col-md-2">
												<form:select id="storeSL" path="store" items="${storeList}" itemValue="id" itemLabel="nameWithDesc" cssClass="uniform"/>
											</div> 
											<label class="control-label col-md-1"><spring:message code="label.dateFrom"/></label>
											<div class="col-md-1">
												<form:select path="monthFrom" cssClass="uniform validate[required]" >
													<c:forEach var="month" items="${reportVM.monthList}">
														<form:option value="${month.code}"><spring:message code="${month.desc}"/></form:option>
													</c:forEach>
												</form:select>
											</div>
											<div class="col-md-1">													
												<form:select path="yearFrom" cssClass="uniform validate[required]" items="${yearList}"></form:select>
											</div>
											<label class="control-label col-md-1"><spring:message code="label.dateTo"/></label>
											<div class="col-md-1">
												<form:select path="monthTo" cssClass="uniform validate[required]" >
													<c:forEach var="month" items="${reportVM.monthList}">
														<form:option value="${month.code}"><spring:message code="${month.desc}"/></form:option>
													</c:forEach>
												</form:select>
											</div>
											<div class="col-md-1">
												<form:select path="yearTo" cssClass="uniform validate[required]" items="${yearList}"></form:select>
											</div>
											<div class="col-md-2">
												<button type="submit" id="btnGenerateReport" class="btn btn-sm btn-green"><spring:message code="label.generate"/></button>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										
									</div>
								</form:form>
							</div>
							
							<!-- table -->
							<div class="container">
								<div class="row">
									<div class="col-md-12">
										<div id="dataTables">
											<table cellpadding="0" cellspacing="0" border="0" class="dTable responsive">
												<thead>
													<tr>
														<th><spring:message code="label.employee"/></th>
														<th><spring:message code="label.workedDaysT"/></th>
														<th><spring:message code="label.goal"/></th>
														<th><spring:message code="label.valueOfSalesT"/></th>
														<th><spring:message code="label.numberOfAttendancesT"/></th>
														<th><spring:message code="label.numberOfSalesT"/></th>
														<th><spring:message code="label.numberOfItemsSoldT"/></th>
														<th><spring:message code="label.achievementOfGoalsT"/></th>
														<th><spring:message code="label.averageValueOfTheProductT"/></th>
														<th><spring:message code="label.averageTicketT"/></th>
														<th><spring:message code="label.itemsPerSaleT"/></th>
														<th><spring:message code="label.conversionRateT"/></th>
														<th><spring:message code="label.averageSalesPerDayT"/></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="indicator" items="${reportVM.reportData.indicators}">
														<tr>
															<td>${indicator.employee.name}</td>
															<td>${indicator.workedDays}</td>
															<td>${indicator.goal}</td>
															<td>${indicator.valueOfSales}</td>
															<td>${indicator.numberOfAttendances}</td>
															<td>${indicator.numberOfSales}</td>
															<td>${indicator.numberOfItemsSold}</td>
															<td>${indicator.achievementOfGoals}</td>
															<td>${indicator.averageValueOfTheProduct}</td>
															<td>${indicator.averageTicket}</td>
															<td>${indicator.itemsPerSale}</td>
															<td>${indicator.conversionRate}</td>
															<td>${indicator.averageSalesPerDay}</td>
														</tr>
													</c:forEach>
														<tr style="font-weight: bold;">
															<td><spring:message code="label.total"/>
															<td>${reportVM.reportData.userData.workedDays}</td>
															<td>${reportVM.reportData.userData.goal}</td>
															<td>${reportVM.reportData.userData.valueOfSales}</td>
															<td>${reportVM.reportData.userData.numberOfAttendances}</td>
															<td>${reportVM.reportData.userData.numberOfSales}</td>
															<td>${reportVM.reportData.userData.numberOfItemsSold}</td>
															<td>${reportVM.reportData.userData.achievementOfGoals}</td>
															<td>${reportVM.reportData.userData.averageValueOfTheProduct}</td>
															<td>${reportVM.reportData.userData.averageTicket}</td>
															<td>${reportVM.reportData.userData.itemsPerSale}</td>
															<td>${reportVM.reportData.userData.conversionRate}</td>
															<td>${reportVM.reportData.userData.averageSalesPerDay}</td>
														</tr>
												</tbody>
											</table>
										</div>	
									</div>
								</div>
							</div>
							<br/>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- [END] main content -->
	</jsp:body>
</t:template>

<script type="text/javascript">
	$(document).ready(function () {
		$("#reportsSL").hide();
	});
</script>