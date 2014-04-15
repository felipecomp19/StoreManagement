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
								<form:form class="form-horizontal fill-up validatable" method="POST" commandName="reportVM" modelAttribute="reportVM" action="#" >
									<div class="padded col-md-8" >
										<div class="form-group"> 
											<label class="control-label col-md-1"><spring:message code="label.dateFrom"/></label>
											<div class="col-md-2">
												<form:select path="monthFrom" cssClass="uniform validate[required]" >
													<c:forEach var="month" items="${reportVM.monthList}">
														<form:option value="${month.code}"><spring:message code="${month.desc}"/></form:option>
													</c:forEach>
												</form:select>
											</div>
											<div class="col-md-2">													
												<form:select path="yearFrom" cssClass="uniform validate[required]" items="${yearList}"></form:select>
											</div>
											<label class="control-label col-md-1"><spring:message code="label.dateTo"/></label>
											<div class="col-md-2">
												<form:select path="monthTo" cssClass="uniform validate[required]" >
													<c:forEach var="month" items="${reportVM.monthList}">
														<form:option value="${month.code}"><spring:message code="${month.desc}"/></form:option>
													</c:forEach>
												</form:select>
											</div>
											<div class="col-md-2">
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
							
							<div class="row">
								<div class="col-md-12">	
									<ul class="padded separate-sections">
										<li>
											<table id="reportCumulativeResult"></table>
											<div id="preportCumulativeResult"></div>
										</li>
									</ul>
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

<script type="text/javascript">
	$(document).ready(function () {
		
		generateReport();
		
		$("form").submit(function (e) {
			var monthFrom = $("#monthFrom").val();
			var yearFrom = $("#yearFrom").val();
			var monthTo = $("#monthTo").val();
			var yearTo = $("#yearTo").val();
			$("#reportCumulativeResult").jqGrid('setGridParam',{
				url: "${pageContext.request.contextPath}/report/generateReportCumulativeResult/" + monthFrom + "/" + yearFrom + "/" + monthTo + "/" + yearTo, 
				datatype:"json", page:1}).trigger("reloadGrid");
			e.preventDefault(); 
		});
		
		function generateReport(){
			var monthFrom = $("#monthFrom").val();
			var yearFrom = $("#yearFrom").val();
			var monthTo = $("#monthTo").val();
			var yearTo = $("#yearTo").val();
			
			$("#reportCumulativeResult").jqGrid({
				url: "${pageContext.request.contextPath}/report/generateReportCumulativeResult/" + monthFrom + "/" + yearFrom + "/" + monthTo + "/" + yearTo,
				datatype: "json",
			 	height: 'auto',
			 	autowidth: true,
			 	jsonReader: {
			        root: "indicators",
			        userdata: "userData",
			        repeatitems: false
				},
				rowNum: 25,
			 	rowList: [10,25,50,100],
			    	colNames:['<spring:message code="label.store" />', 
			    	          '<spring:message code="label.employee"/>', 
			    	          '<spring:message code="label.workedDaysT"/>',
			    	          '<spring:message code="label.goal"/>',
			    	          '<spring:message code="label.valueOfSalesT"/>',
			    	          '<spring:message code="label.numberOfAttendancesT"/>',
			    	          '<spring:message code="label.numberOfSalesT"/>',
			    	          '<spring:message code="label.numberOfItemsSoldT"/>',
			    	          '<spring:message code="label.achievementOfGoalsT"/>',
			    	          '<spring:message code="label.averageValueOfTheProductT"/>',
			    	          '<spring:message code="label.averageTicketT"/>',
			    	          '<spring:message code="label.itemsPerSaleT"/>',
			    	          '<spring:message code="label.conversionRateT"/>',
			    	          '<spring:message code="label.averageSalesPerDayT"/>'
	   	          	],
			    	colModel:[
			    		{name:'employee.store.nameWithDesc',index:'employee.store.nameWithDesc', width:90},
			    		{name:'employee.name',index:'employee.name', width:100 },
			    		{name:'workedDays',index:'workedDays', width:80, align:"right",sorttype:"int", formatter:"integer"},
			    		{name:'goal',index:'goal', width:80, align:"right",sorttype:"float", formatter:"number", summaryType:'sum', summaryTpl:'<b>{0}</b>'},
			    		{name:'valueOfSales',index:'valueOfSales', width:80, align:"right",sorttype:"float",formatter:"number", summaryType:'sum', summaryTpl:'<b>{0}</b>'},
			    		{name:'numberOfAttendances',index:'numberOfAttendances', width:80, align:"right",sorttype:"int", summaryType:'sum', summaryTpl:'<b>{0}</b>'},
			    		{name:'numberOfSales',index:'numberOfSales', width:80, align:"right",sorttype:"int", summaryType:'sum', summaryTpl:'<b>{0}</b>'},
			    		{name:'numberOfItemsSold',index:'numberOfItemsSold', width:80, align:"right",sorttype:"int", summaryType:'sum', summaryTpl:'<b>{0}</b>'},
			    		{name:'achievementOfGoals',index:'achievementOfGoals', width:80, align:"right",sorttype:"float",formatter:"number", editable:true, summaryType:'avg', summaryTpl:'<b>{0}</b>'},
			    		{name:'averageValueOfTheProduct',index:'averageValueOfTheProduct', width:80, align:"right",sorttype:"float",formatter:"number", editable:true, summaryType:'avg', summaryTpl:'<b>{0}</b>'},
			    		{name:'averageTicket',index:'averageTicket', width:80, align:"right",sorttype:"float",formatter:"number", editable:true, summaryType:'avg', summaryTpl:'<b>{0}</b>'},
			    		{name:'itemsPerSale',index:'itemsPerSale', width:80, align:"right",sorttype:"float",formatter:"number", editable:true, summaryType:'avg', summaryTpl:'<b>{0}</b>'},
			    		{name:'conversionRate',index:'conversionRate', width:80, align:"right",sorttype:"float",formatter:"number", editable:true, summaryType:'avg', summaryTpl:'<b>{0}</b>'},
			    		{name:'averageSalesPerDay',index:'averageSalesPerDay', width:80, align:"right",sorttype:"float",formatter:"number", editable:true, summaryType:'avg', summaryTpl:'<b>{0}</b>'}
			    	],
			    	pager: "#preportCumulativeResult",
			    	viewrecords: true,
			    	sortname: 'employee.store.nameWithDesc',
			    	grouping: true,
			    	groupingView : {
			    		groupField : ['employee.store.nameWithDesc'],
			    		groupSummary : [true],
			    		groupColumnShow : [false],
			    		groupText : ['<b>{0}</b>'],
			    		groupCollapse : false,
			 			groupOrder: ['asc'],
			 			groupDataSorted : true
			    	},
			        footerrow: true,
			        userDataOnFooter: true
			 }).trigger("reloadGrid");
		}
		
		
	});
</script>