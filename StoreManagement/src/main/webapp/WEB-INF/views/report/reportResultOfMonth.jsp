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
									<form:form class="form-horizontal fill-up validatable" method="POST" commandName="reportVM" modelAttribute="reportVM" action="${pageContext.request.contextPath}/report/generate" >
										<form:select id="reportsSL" path="selectedReport">
											<form:option value="1"><spring:message code="report.resultOfMonth"/></form:option>
										</form:select>
										<div class="padded col-md-12" >
											<div class="form-group"> 
												<label class="control-label col-md-1"><spring:message code="label.store"/></label>
												<div class="col-md-2">
													<form:select id="storeSL" path="store" items="${storeList}" itemValue="id" itemLabel="nameWithDesc" cssClass="uniform"/>
												</div>
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
							
							
							<!-- table -->
							<div class="container">
								<div class="row">
									<div class="col-md-12">
										<div class="box">
											<div class="box-header">
												<ul class="box-toolbar">
													<li><button class="btn btn-sm btn-blue" id="exportReport" ><i class="icon-download-alt"></i>&nbsp;&nbsp;<spring:message code="label.exportToExcel"/></button>
    											</ul>
											</div>
											<div class="box-content">
												<div id="dataTables">
													<table cellpadding="0" cellspacing="0" border="0" class="dTable responsive">
														<thead>
															<tr>
																<th width="40px;"><i class="icon-bar-chart"></i></th>
																<th><spring:message code="label.employee"/></th>
																<th><spring:message code="label.workedDaysT"/></th>
																<th><spring:message code="label.goalT"/></th>
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
																	<td>
																		<input class="checkEmp" type="checkbox" checked="checked" value="${indicator.employee.id}" onclick="reloadGraph()">
																	</td>
																	<td>${indicator.employee.name}</td>
																	<td >${indicator.workedDays}</td>
																	<td class="indInput money">${indicator.goal}</td>
																	<td>${indicator.valueOfSales}</td>
																	<td>${indicator.numberOfAttendances}</td>
																	<td>${indicator.numberOfSales}</td>
																	<td>${indicator.numberOfItemsSold}</td>
																	<td>${indicator.formattedAchievementOfGoals}</td>
																	<td>${indicator.averageValueOfTheProduct}</td>
																	<td>${indicator.averageTicket}</td>
																	<td>${indicator.itemsPerSale}</td>
																	<td>${indicator.formattedConversionRate}</td>
																	<td>${indicator.averageSalesPerDay}</td>
																</tr>
															</c:forEach>
														</tbody>
														<tfoot style="font-weight: bold;">
															<tr>
																<td></td>
																<td><spring:message code="label.total"/>
																<td>${reportVM.reportData.userData.workedDays}</td>
																<td>${reportVM.reportData.userData.goal}</td>
																<td>${reportVM.reportData.userData.valueOfSales}</td>
																<td>${reportVM.reportData.userData.numberOfAttendances}</td>
																<td>${reportVM.reportData.userData.numberOfSales}</td>
																<td>${reportVM.reportData.userData.numberOfItemsSold}</td>
																<td>${reportVM.reportData.userData.formattedAchievementOfGoals}</td>
																<td>${reportVM.reportData.userData.averageValueOfTheProduct}</td>
																<td>${reportVM.reportData.userData.averageTicket}</td>
																<td>${reportVM.reportData.userData.itemsPerSale}</td>
																<td>${reportVM.reportData.userData.formattedConversionRate}</td>
																<td>${reportVM.reportData.userData.averageSalesPerDay}</td>
															</tr>
														</tfoot>
													</table>
												</div>
											</div>
										</div>	
									</div>
								</div>
							</div>
							
							<br/>
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
		
		$("#reportsSL").hide();
		
		populateChart(null);
        
        $("#indSL").change(function(){
        	//populateChart();
        	reloadGraph();
        });
        
        $("#exportReport").click(function(){
        	var selectedStore = $("#storeSL").val();
        	month = $("#selectedMonth").val();
			year = $("#selectedYear").val();
			
			window.location.replace("${pageContext.request.contextPath}/report/exportReportResultOfMonth/" + selectedStore + "/" + month + "/" + year);
        });
	});
	
	function populateChart(selectedEmps){
		var selectedInd = $("#indSL").val();
    	var selectedStore = $("#storeSL").val();
    	month = $("#selectedMonth").val();
		year = $("#selectedYear").val();
		
		//css para os graficos
		var tt = document.createElement('div'),leftOffset = -(~~$('html').css('padding-left').replace('px', '') + ~~$('body').css('margin-left').replace('px', '')),topOffset = -32;
		tt.className = 'ex-tooltip';
		document.body.appendChild(tt);
		
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
   		 			var id = parseInt(this.employee.id);
   		 			if(selectedEmps == null || jQuery.inArray(id, selectedEmps) >= 0){
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
   		 			}
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
        });
        
        return data;
	}
	
	function reloadGraph()
	{
		var selectedEmps = [];
		var i = 0;
		var selected = $('.checkEmp:checkbox:checked').each(function(){
			selectedEmps[i] = parseInt($(this).val());
			i++;
		});
		
		populateChart(selectedEmps);
	}
</script>