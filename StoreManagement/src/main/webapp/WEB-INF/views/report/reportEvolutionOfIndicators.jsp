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
				        	<input type="hidden" id="monthFrom" value="${reportVM.monthFrom}">
							<input type="hidden" id="yearFrom" value="${reportVM.yearFrom}">
							<input type="hidden" id="monthTo" value="${reportVM.monthTo}">
							<input type="hidden" id="yearTo" value="${reportVM.yearTo}">
			
				        	<ul class="nav nav-tabs nav-tabs-right">
				          		<li class="active"><a href="#tabAchievementOfGoals" data-toggle="tab"><spring:message code="label.achievementOfGoals"/></a></li>
				          		<li><a href="#messages2" data-toggle="tab"><i class="icon-comments-alt"></i> <span>Messages</span></a></li>
				          		<li><a href="#settings2" data-toggle="tab"><i class="icon-cog"></i> <span>Settings</span></a></li>
				        	</ul>
		      			</div>
				      	<div class="box-content padded">
					        <div class="tab-content">
					          	<div class="tab-pane active" id="tabAchievementOfGoals">
					          		<div class="row">
					          		<table>
					          			<thead>
					          				<tr>
					          					<th>
					          						<spring:message code="label.name" />
					          					</th>
					          					<c:forEach var="obj" items="${reportVM.headers}">
					          						<th>	
					          							<c:out value="${obj}" />
					          						</th>
					          					</c:forEach>
					          				</tr>
					          			</thead>
					          			<c:forEach var="ind" items="${reportVM.indicators}">
					          				
					          			
					          			</c:forEach>
					          		</table>
					          		</div>
									<%-- <table id="reportAchievementOfGoals"></table>
									<div id="preportAchievementOfGoals"></div> --%>
									<div class="row">
									<table id="reportEvolutionOfIndicators"></table>
									<div id="preportEvolutionOfIndicators"></div>
									</div>
								</div>
					          <div class="tab-pane" id="profile2">And now to the profile</div>
					          <div class="tab-pane" id="messages2">No new messages</div>
					          <div class="tab-pane" id="settings2">Changing the settings</div>
					        </div>
			       		</div>
				    </div>
				</div>
			</div>
		</div>
		<!-- [END] main content -->
	</jsp:body>
</t:template>

<script type="text/javascript" >
	$(document).ready(function () {
		var monthFrom = $("#monthFrom").val();
		var yearFrom = $("#yearFrom").val();
		var monthTo = $("#monthTo").val();
		var yearTo = $("#yearTo").val();
		
		$("#reportAchievementOfGoals").jqGrid('jqPivot', 
			{"rows":[
				{
					"CategoryName":"Beverages",
					"ProductName":"Steeleye Stout",
					"Country":"UK",
					"Price":"1008.0000",
					"Quantity":"65"
				},{
					"CategoryName":"Beverages",
					"ProductName":"Laughing Lumberjack Lager",
					"Country":"USA",
					"Price":"140.0000",
					"Quantity":"10"
				}
			]},
				// pivot options
				{
		xDimension : [
			{
				dataName: 'CategoryName', 
				width:90
			},{
				dataName: 'ProductName'
			}
		],
		yDimension : [
			{
				dataName: 'Country'
			}
		],
		aggregates : [
			{ 
				member : 'Price', 
				aggregator : 'sum', 
				width:80,
				formatter:'number',
				align:'right',
				summaryType: 'sum'
			}
		],
		rowTotals: true,
		colTotals: true
	}, 
	// grid options
	{
		width: 700,
		rowNum : 10,
		pager: "#ppager4",
		caption: "Totals by columns and rows"
	});
		

		
		$("#reportEvolutionOfIndicators").jqGrid({
			url: "${pageContext.request.contextPath}/report/generateReportevolutionOfIndicators/" + monthFrom + "/" + yearFrom + "/" + monthTo + "/" + yearTo,
			datatype: "json",
		 	height: 'auto',
		 	autowidth: true,
		 	rowNum: 30,
		 	rowList: [10,20,30],
		    	colNames:['<spring:message code="label.store" />', 
		    	          '<spring:message code="label.employee"/>',
		    	          '<spring:message code="label.year"/>',
		    	          '<spring:message code="label.month"/>',
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
		    		{name:'employee.store.name',index:'employee.store.name', width:90},
		    		{name:'employee.name',index:'employee.name', width:100 },
		    		{name:'year',index:'year', width:50},
		    		{name:'month',index:'month', width:50 },
		    		{name:'workedDays',index:'workedDays', width:80, align:"right",sorttype:"int", formatter:"number", summaryType:'max', summaryTpl:'<b>Max: {0}</b>'},
		    		{name:'goal',index:'goal', width:80, align:"right",sorttype:"float", formatter:"number", summaryType:'sum', summaryTpl:'<b>Total: {0}</b>'},
		    		{name:'valueOfSales',index:'valueOfSales', width:80, align:"right",sorttype:"float",formatter:"number", summaryType:'sum', summaryTpl:'<b>Total: {0}</b>'},
		    		{name:'numberOfAttendances',index:'numberOfAttendances', width:80, align:"right",sorttype:"int", summaryType:'sum', summaryTpl:'<b>Total: {0}</b>'},
		    		{name:'numberOfSales',index:'numberOfSales', width:80, align:"right",sorttype:"int", summaryType:'sum', summaryTpl:'<b>Total: {0}</b>'},
		    		{name:'numberOfItemsSold',index:'numberOfItemsSold', width:80, align:"right",sorttype:"int", summaryType:'sum', summaryTpl:'<b>Total: {0}</b>'},
		    		{name:'achievementOfGoals',index:'achievementOfGoals', width:80, align:"right",sorttype:"float",formatter:"number", editable:true, summaryType:'avg', summaryTpl:'<b>Media: {0}</b>'},
		    		{name:'averageValueOfTheProduct',index:'averageValueOfTheProduct', width:80, align:"right",sorttype:"float",formatter:"number", editable:true, summaryType:'avg', summaryTpl:'<b>Media: {0}</b>'},
		    		{name:'averageTicket',index:'averageTicket', width:80, align:"right",sorttype:"float",formatter:"number", editable:true, summaryType:'avg', summaryTpl:'<b>Media: {0}</b>'},
		    		{name:'itemsPerSale',index:'itemsPerSale', width:80, align:"right",sorttype:"float",formatter:"number", editable:true, summaryType:'avg', summaryTpl:'<b>Media: {0}</b>'},
		    		{name:'conversionRate',index:'conversionRate', width:80, align:"right",sorttype:"float",formatter:"number", editable:true, summaryType:'avg', summaryTpl:'<b>Media: {0}</b>'},
		    		{name:'averageSalesPerDay',index:'averageSalesPerDay', width:80, align:"right",sorttype:"float",formatter:"number", editable:true, summaryType:'avg', summaryTpl:'<b>Media: {0}</b>'}
		    	],
		    	pager: "#preportEvolutionOfIndicators",
		    	viewrecords: true,
		    	sortname: 'employee.store.name',
		    	grouping: true,
		    	groupingView : {
		    		groupField : ['employee.store.name'],
		    		groupSummary : [true],
		    		groupColumnShow : [false],
		    		groupText : ['<b>{0}</b>'],
		    		groupCollapse : false,
		 			groupOrder: ['asc'],
		 			groupDataSorted : true
		    	}
		 });
	});
</script>

<!-- <script type="text/javascript">
	$(document).ready(function () {
		var mydata = [
			      		{id:"1",invdate:"2010-05-24",name:"test",note:"note",tax:"10.00",total:"2111.00"} ,
			      		{id:"2",invdate:"2010-05-25",name:"test2",note:"note2",tax:"20.00",total:"320.00"},
			      		{id:"3",invdate:"2007-09-01",name:"test3",note:"note3",tax:"30.00",total:"430.00"},
			      		{id:"4",invdate:"2007-10-04",name:"test",note:"note",tax:"10.00",total:"210.00"},
			      		{id:"5",invdate:"2007-10-05",name:"test2",note:"note2",tax:"20.00",total:"320.00"},
			      		{id:"6",invdate:"2007-09-06",name:"test3",note:"note3",tax:"30.00",total:"430.00"},
			      		{id:"7",invdate:"2007-10-04",name:"test",note:"note",tax:"10.00",total:"210.00"},
			      		{id:"8",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"21.00",total:"320.00"},
			      		{id:"9",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
			      		{id:"11",invdate:"2007-10-01",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
			      		{id:"12",invdate:"2007-10-02",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
			      		{id:"13",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
			      		{id:"14",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
			      		{id:"15",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
			      		{id:"16",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
			      		{id:"17",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
			      		{id:"18",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
			      		{id:"19",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
			      		{id:"21",invdate:"2007-10-01",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
			      		{id:"22",invdate:"2007-10-02",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
			      		{id:"23",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
			      		{id:"24",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
			      		{id:"25",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
			      		{id:"26",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
			      		{id:"27",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
			      		{id:"28",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
			      		{id:"29",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}
			      	];
			      $("#list486").jqGrid({
			      	data: mydata,
			      	datatype: "local",
			      	height: 'auto',
			      	rowNum: 30,
			      	rowList: [10,20,30],
			         	colNames:['Inv No','Date', 'Client', 'Amount','Tax','Total','Notes'],
			         	colModel:[
			         		{name:'id',index:'id', width:60, sorttype:"int"},
			         		{name:'invdate',index:'invdate', width:90, sorttype:"date", formatter:"date"},
			         		{name:'name',index:'name', width:100, editable:true},
			         		{name:'amount',index:'amount', width:80, align:"right",sorttype:"float", formatter:"number", editable:true, summaryType:'min', summaryTpl:'<b>Min: {0}</b>'},
			         		{name:'tax',index:'tax', width:80, align:"right",sorttype:"float",formatter:"number", editable:true, summaryType:'max', summaryTpl:'<b>Max: {0}</b>'},
			         		{name:'total',index:'total', width:80,align:"right",sorttype:"float", formatter:"number", summaryType:'sum'},		
			         		{name:'note',index:'note', width:150, sortable:false, summaryType:'count', summaryTpl:'<b>{0} Item(s)</b>'}		
			         	],
			         	pager: "#plist486",
			         	viewrecords: true,
			         	sortname: 'name',
			         	grouping:true,
			         	groupingView : {
			         		groupField : ['name'],
			         		groupSummary : [true],
			         		groupColumnShow : [true],
			         		groupText : ['<b>{0}</b>'],
			         		groupCollapse : false,
			      		groupOrder: ['asc']
			         	},
			         	caption: "Summary footers"
			      });
	});
</script> -->