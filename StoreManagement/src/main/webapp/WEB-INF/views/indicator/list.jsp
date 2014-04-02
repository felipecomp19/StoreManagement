<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<t:template>
	<jsp:body>
		
		<!-- top main content -->
		<%@include file="topMainContent.jsp"%>
		<!-- [END] top main content -->
		
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
						<span class="breadcrumb-label"> <i class="icon-tasks"></i>
							<spring:message code="label.indicators" />
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
							<span class="title"><spring:message code="label.indicators" /></span>
							<ul class="box-toolbar">
								<li>
									<a href="${pageContext.request.contextPath}/indicator/create">
									<button class="btn btn-green">
										<i class="icon-tasks"></i>&nbsp&nbsp<spring:message code="label.newIndicator" />
									</button>
									</a>
									<button id="exchangeToIndBtn" class="btn btn-blue indInput">
										<i class="icon-exchange"></i>&nbsp&nbsp<spring:message code="label.indicators" />
									</button>
									<button id="exchangeToIndInputBtn" class="btn btn-blue ind">
										<i class="icon-exchange"></i>&nbsp&nbsp<spring:message code="label.indicators" />
									</button>
								</li>
							</ul>
						</div>
						<div class="box-content">
							<div id="dataTables">
								<table cellpadding="0" cellspacing="0" border="0" class="dTable responsive">
									<thead>
										<tr>
											<th style="width:50px;"></th>
											<th><div><spring:message code="label.store" /></div></th>
											<th><div><spring:message code="label.employee"/></div></th>
											<th><div><spring:message code="label.year"/></div></th>
											<th><div><spring:message code="label.month"/></div></th>
											<th class="indInput"><div><spring:message code="label.workedDaysT"/></div></th>
											<th class="indInput"><div><spring:message code="label.goal"/></div></th>
											<th class="indInput"><div><spring:message code="label.valueOfSalesT"/></div></th>
											<th class="indInput"><div><spring:message code="label.numberOfAttendancesT"/></div></th>
											<th class="indInput"><div><spring:message code="label.numberOfSalesT"/></div></th>
											<th class="indInput"><div><spring:message code="label.numberOfItemsSoldT"/></div></th>
											<th class="ind" hidden="hidden"><div><spring:message code="label.achievementOfGoalsT"/></div></th>
											<th class="ind" hidden="hidden"><div><spring:message code="label.averageValueOfTheProductT"/></div></th>
											<th class="ind" hidden="hidden"><div><spring:message code="label.averageTicketT"/></div></th>
											<th class="ind" hidden="hidden"><div><spring:message code="label.itemsPerSaleT"/></div></th>
											<th class="ind" hidden="hidden"><div><spring:message code="label.conversionRateT"/></div></th>
											<th class="ind" hidden="hidden"><div><spring:message code="label.averageSalesPerDayT"/></div></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="indicator" items="${indicators}">
											<tr>
												<td class="center"> 
													<div class="btn-group">
														<button class="btn btn-xs btn-default dropdown-toggle" data-toggle="dropdown">
															<i class="icon-cog"></i>
														</button>
														<ul class="dropdown-menu">
															<li>
																<a href="${pageContext.request.contextPath}/indicator/edit/${indicator.id}"><spring:message code="label.edit"/></a>
															</li>
															<li class="divider"/>
															<li>
																<a href="${pageContext.request.contextPath}/indicator/delete/${indicator.id}"><spring:message code="label.delete"/></a>
															</li>
														</ul>
													</div>
												</td>
												<td>${indicator.employee.store.nameWithDesc}</td>
												<td>${indicator.employee.name}</td>
												<td>${indicator.year}</td>
												<td>${indicator.month}</td>
												<td class="indInput">${indicator.workedDays}</td>
												<td class="indInput money">${indicator.goal}</td>
												<td class="indInput">${indicator.valueOfSales}</td>
												<td class="indInput">${indicator.numberOfAttendances}</td>
												<td class="indInput">${indicator.numberOfSales}</td>
												<td class="indInput">${indicator.numberOfItemsSold}</td>
												<td class="ind" hidden="hidden">${indicator.achievementOfGoals}</td>
												<td class="ind" hidden="hidden">${indicator.averageValueOfTheProduct}</td>
												<td class="ind" hidden="hidden">${indicator.averageTicket}</td>
												<td class="ind" hidden="hidden">${indicator.itemsPerSale}</td>
												<td class="ind" hidden="hidden">${indicator.conversionRate}</td>
												<td class="ind" hidden="hidden">${indicator.averageSalesPerDay}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
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
		$("#exchangeToIndBtn").click(function(){
			$(".ind").show();
			$(".indInput").hide();
		});
		
		$("#exchangeToIndInputBtn").click(function(){
			$(".ind").hide();
			$(".indInput").show();
		});
		
		$("#exchangeToIndInputBtn").hide();
		
	});
</script>