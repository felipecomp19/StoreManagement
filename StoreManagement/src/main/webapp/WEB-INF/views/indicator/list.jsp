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
						<span class="breadcrumb-label"> <i class="icon-book"></i>
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
										<i class="icon-book"></i>&nbsp&nbsp<spring:message code="label.newIndicator" />
									</button>
									</a>
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
											<th><div><spring:message code="label.workedDaysT"/></div></th>
											<th><div><spring:message code="label.goal"/></div></th>
											<th><div><spring:message code="label.valueOfSalesT"/></div></th>
											<th><div><spring:message code="label.numberOfAttendancesT"/></div></th>
											<th><div><spring:message code="label.numberOfSalesT"/></div></th>
											<th><div><spring:message code="label.numberOfItemsSoldT"/></div></th>
											<th><div><spring:message code="label.achievementOfGoalsT"/></div></th>
											<th><div><spring:message code="label.averageValueOfTheProductT"/></div></th>
											<th><div><spring:message code="label.averageTicketT"/></div></th>
											<th><div><spring:message code="label.itemsPerSaleT"/></div></th>
											<th><div><spring:message code="label.conversionRateT"/></div></th>
											<th><div><spring:message code="label.averageSalesPerDayT"/></div></th>
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
												<td>${indicator.workedDays}</td>
												<td class="money">${indicator.goal}</td>
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