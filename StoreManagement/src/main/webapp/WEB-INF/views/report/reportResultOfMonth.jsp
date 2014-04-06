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
							<span><spring:message code="report.resultOfMonth" /></span>
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
							<spring:message code="report.resultOfMonth" />
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
							<span class="title"><spring:message code="report.resultOfMonth" /></span>
						</div>
						<div class="box-content">
							<div class="row">
								<div class="col-md-12" >
									<form:form class="form-horizontal fill-up validatable" method="POST" commandName="reportVM" modelAttribute="reportVM" action="#" >
										<div class="padded col-md-8" >
											<label><spring:message code="label.selectMonth"/></label>
											<div class="form-group"> 
												<label class="control-label col-md-1"><spring:message code="label.month" /></label>
												<div class="col-md-2">
													<form:select path="selectedMonth" cssClass="uniform validate[required]" >
														<c:forEach var="month" items="${reportVM.monthList}">
															<form:option value="${month.code}"><spring:message code="${month.desc}"/></form:option>
														</c:forEach>
													</form:select>
												</div>
												<label class="control-label col-md-1"><spring:message code="label.year" /></label>
												<div class="col-md-2">
													<form:select path="selectedYear" cssClass="uniform validate[required]" items="${yearList}"></form:select>
												</div>
												<div class="col-md-2">
													<button type="submit" id="btnGenerateReport" class="btn btn-sm btn-green"><spring:message code="label.generate"/></button>
												</div>
											</div>
										</div>
									</form:form>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-12">	
									<ul class="padded separate-sections">
										<li>
											<table id="reportResultOfMonth"></table>
											<div id="preportResultOfMonth"></div>
										</li>
									</ul>
								</div>
							</div>
							
							<!-- charts -->
							<div class="container">
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
														<select id="indSL" class="uniform">
															<option value="1"><spring:message code="label.achievementOfGoals"/></option>
															<option value="2"><spring:message code="label.averageValueOfTheProduct"/></option>
															<option value="3"><spring:message code="label.averageTicket"/></option>
															<option value="4"><spring:message code="label.itemsPerSale"/></option>
															<option value="5"><spring:message code="label.conversionRate"/></option>
															<option value="6"><spring:message code="label.averageSalesPerDay"/></option>
														</select>
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
												
												<div id="indChart" class="xcharts-indChart" style="width: 100%; height: 300px">
												</div>
											</div>
										</div>
									</div>
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
		var month = $("#selectedMonth").val();
		var year = $("#selectedYear").val();
		
		$("#reportResultOfMonth").jqGrid({
			url: "${pageContext.request.contextPath}/report/generateReportResultOfMonth/" + month + "/" + year,
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
		    	pager: "#preportResultOfMonth",
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
		 });
		
		
		
		//css para os graficos
		var tt = document.createElement('div'),leftOffset = -(~~$('html').css('padding-left').replace('px', '') + ~~$('body').css('margin-left').replace('px', '')),topOffset = -32;
		tt.className = 'ex-tooltip';
		document.body.appendChild(tt);
		
		var populateChart = function(){
			var selectedInd = $("#indSL").val();
        	var selectedStore = $("#storeSL").val();
        	month = $("#selectedMonth").val();
			year = $("#selectedYear").val();
			
			var data = {
	                "xScale": "ordinal",
	                "yScale": "linear"
	            };

            data.main = [];
            var graphData = [];
            $.blockUI({
   	            message: '<h3> Consultando <img src="${pageContext.request.contextPath}/resources/coreAdmin/images/loading.gif" /></h3>'
   	        });
            
            $.ajax({
	   			url: "${pageContext.request.contextPath}/report/getIndicatorsByStoreMonthAndYear/" + selectedStore + "/" + month + "/" +year,
	   			type:"GET",
	   			dataType: "json",
	   			contentType: 'application/json',
	   		    mimeType: 'application/json', 
	   		 	success: function(result) { 
	   		 		$.each(result.indicators, function(){
						if(selectedInd == 1) //achievementOfGoals
			   		 		graphData.push({
		                        "x": this.employee.name,
		                        "y": this.achievementOfGoals
		                    });
						else if(selectedInd == 2)//averageValueOfTheProduct
							graphData.push({
		                        "x": this.employee.name,
		                        "y": this.averageValueOfTheProduct
		                    });
						else if(selectedInd == 3)//averageTicket
							graphData.push({
		                        "x": this.employee.name,
		                        "y": this.averageTicket
		                    });
						else if(selectedInd == 4)//itemsPerSale
							graphData.push({
		                        "x": this.employee.name,
		                        "y": this.itemsPerSale
		                    });
						else if(selectedInd == 5)//conversionRate
							graphData.push({
		                        "x": this.employee.name,
		                        "y": this.conversionRate
		                    });
						else if(selectedInd == 6)//averageSalesPerDay
							graphData.push({
		                        "x": this.employee.name,
		                        "y": this.averageSalesPerDay
		                    });
					});
		   		 	data.main.push({
		                "className": ".indicators",
		                "data": graphData
		            });
		   		 
	   	    	},
	   	    	error:function(data,status,er) { 
	   	    		return Growl.error({
                        title:'Erro!',
                        text: 'Erro ao criar graficos'
                    });
	   	     	},
                complete: function() {
                	new xChart('bar', data, '#indChart', {
                		axisPaddingTop: 5, 
                		paddingLeft: 25,
	    	        	mouseover: function (d, i) {
	    	            	var pos = $(this).offset();
	    	            	$(tt).text(d.y).css({top: topOffset + pos.top, left: pos.left + leftOffset}).show();
	    	          	},
	    	          	mouseout: function (x) { $(tt).hide(); }
                	});
	
                	
                	var c = 0;
                	$("g.indicators").attr("class","main indicators bar");
                	$(".indicators").find("rect").each(function(){
                		$(this).addClass("color" + c);
                		$(this).attr("class", "color" + c);
                		if(c >= 9)
                			c = 0;
                		c++;
    	          	});

                    $.unblockUI();
                }
            }).done(function(){
            	
            });
            
            return data;
		};
		
		populateChart();
        
        $("#indSL").change(function(){
        	populateChart();
        });
        
        $("#storeSL").change(function(){
        	populateChart();
        });
        
        $("form").submit(function (e) {
			month = $("#selectedMonth").val();
			year = $("#selectedYear").val();
			$("#reportResultOfMonth").jqGrid('setGridParam',{
				url: "${pageContext.request.contextPath}/report/generateReportResultOfMonth/" + month + "/" + year, 
				datatype:"json", page:1}).trigger("reloadGrid");
			 
			
			populateChart();
			
			e.preventDefault();
		});
	});
</script>