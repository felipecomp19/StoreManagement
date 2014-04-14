<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
							<span><spring:message code="report.evolutionOfIndicators" /></span>
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
							<spring:message code="report.evolutionOfIndicators" />
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
				        	<div class="title"><spring:message code="report.evolutionOfIndicators"/></div>
			
				        	<ul class="nav nav-tabs nav-tabs-right">
				          		<li class="active"><a href="#tabAchievementOfGoals" data-toggle="tab"><spring:message code="label.achievementOfGoals"/></a></li>
				          		<li><a href="#tabAverageValueOfTheProduct" data-toggle="tab"><spring:message code="label.averageValueOfTheProduct" /></a></li>
				          		<li><a href="#tabAverageTicket" data-toggle="tab"><spring:message code="label.averageTicket"/></a></li>
				          		
				          		<li><a href="#tabItemsPerSale" data-toggle="tab"><spring:message code="label.itemsPerSale"/></a></li>
				          		<li><a href="#tabConversionRate" data-toggle="tab"><spring:message code="label.conversionRate"/></a></li>
				          		<li><a href="#tabAverageSalesPerDay" data-toggle="tab"><spring:message code="label.averageSalesPerDay"/></a></li>
				        	</ul>
		      			</div>
				      	<div class="box-content padded">
					        <div class="tab-content">
					          	<div class="tab-pane active" id="tabAchievementOfGoals">
				          			<%@include file="achievementOfGoalsDataTable.jsp"%>
								</div>
					          	<div class="tab-pane" id="tabAverageValueOfTheProduct">
					          		<%@include file="averageValueOfTheProductDataTable.jsp"%>
				          		</div>
					          	<div class="tab-pane" id="tabAverageTicket">
					          		<%@include file="averageTicketMapDataTable.jsp"%>
				          		</div>
				          		<div class="tab-pane" id="tabItemsPerSale">
					          		<%@include file="itemsPerSaleMapDataTable.jsp"%>
				          		</div>
				          		<div class="tab-pane" id="tabConversionRate">
					          		<%@include file="conversionRateMapDataTable.jsp"%>
				          		</div>
				          		<div class="tab-pane" id="tabAverageSalesPerDay">
					          		<%@include file="averageSalesPerDayMapDataTable.jsp"%>
				          		</div>
					        </div>
			       		</div>
				    </div>
				</div>
			</div>
		</div>
		<!-- [END] main content -->
	</jsp:body>
</t:template>