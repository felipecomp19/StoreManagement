<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
				          		<li class="active"><a id="tabRef1" href="#tabAchievementOfGoals" data-toggle="tab"><spring:message code="label.achievementOfGoals"/></a></li>
				          		<li><a id="tabRef2" href="#tabAverageValueOfTheProduct" data-toggle="tab" ><spring:message code="label.averageValueOfTheProduct" /></a></li>
				          		<li><a id="tabRef3" href="#tabAverageTicket" data-toggle="tab"><spring:message code="label.averageTicket"/></a></li>
				          		
				          		<li><a id="tabRef4" href="#tabItemsPerSale" data-toggle="tab"><spring:message code="label.itemsPerSale"/></a></li>
				          		<li><a id="tabRef5" href="#tabConversionRate" data-toggle="tab"><spring:message code="label.conversionRate"/></a></li>
				          		<li><a id="tabRef6" href="#tabAverageSalesPerDay" data-toggle="tab"><spring:message code="label.averageSalesPerDay"/></a></li>
				        	</ul>
		      			</div>
				      	<div class="box-content padded">
				      		<div class="row">
								<form:form class="form-horizontal fill-up validatable" method="POST" commandName="reportVM" modelAttribute="reportVM" action="${pageContext.request.contextPath}/report/generate" >
									<form:select id="reportsSL" path="selectedReport">
										<form:option value="3"><spring:message code="report.evolutionOfIndicators"/></form:option>
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
					        <div class="tab-content">
					          	<div class="tab-pane active" id="tabAchievementOfGoals">
				          			<%@include file="achievementOfGoalsDataTable.jsp"%>
								</div>
					          	<div class="tab-pane active" id="tabAverageValueOfTheProduct">
					          		<%@include file="averageValueOfTheProductDataTable.jsp"%>
				          		</div>
					          	<div class="tab-pane active" id="tabAverageTicket">
					          		<%@include file="averageTicketMapDataTable.jsp"%>
				          		</div>
				          		<div class="tab-pane active" id="tabItemsPerSale">
					          		<%@include file="itemsPerSaleMapDataTable.jsp"%>
				          		</div>
				          		<div class="tab-pane active" id="tabConversionRate">
					          		<%@include file="conversionRateMapDataTable.jsp"%>
				          		</div>
				          		<div class="tab-pane active" id="tabAverageSalesPerDay">
					          		<%@include file="averageSalesPerDayMapDataTable.jsp"%>
				          		</div>
					        </div>
			       		</div>
				    </div>
				</div>
			</div>
		</div>
		<!-- [END] main content -->
		
<script type="text/javascript">
	$(document).ready(function() {
		$("#reportsSL").hide();
		
		var tt = document.createElement('div'),leftOffset = -(~~$('html').css('padding-left').replace('px', '') + ~~$('body').css('margin-left').replace('px', '')),topOffset = -32;
		tt.className = 'ex-tooltip';
		document.body.appendChild(tt);
		
		
		populateChart("#achievementOfGoalChart","#achievementOfGoalChart_legend","employees","graphData");
		populateChartMoney("#averageValueOfTheProductChart","#averageValueOfTheProductChart_legend","employees2","graphData2");
		populateChartMoney("#averageTicketChart","#averageTicketChart_legend","employees3","graphData3");
		populateChartStatic("#itemsPerSaleChart","#itemsPerSaleChart_legend","employees4","graphData4");
		populateChart("#conversionRateChart","#conversionRateChart_legend","employees5","graphData5");
		populateChartMoney("#averageSalesPerDayChart","#averageSalesPerDayChart_legend","employees6","graphData6");
		
		
		$("#tab1").hide();
		$("#tab2").hide();
		$("#tab3").hide();
		$("#tab4").hide();
		$("#tab5").hide();
		$("#tab6").hide();
		$("#tabRef1").click(function(){
			$("#tab1").show();
		});
		$("#tabRef2").click(function(){
			$("#tab2").show();
		});
		$("#tabRef3").click(function(){
			$("#tab3").show();
		});
		$("#tabRef4").click(function(){
			$("#tab4").show();
		});
		$("#tabRef5").click(function(){
			$("#tab5").show();
		});
		$("#tabRef6").click(function(){
			$("#tab6").show();
		});
		
		
		function getData(chartLegendId, emps, gData){
	        var data = {
	        	 "xScale":"ordinal",
	             "comp":[],
	             "yScale":"linear",
	             "type":"line-dotted"
	        };

	        data.main = [];
	        
	        var employees = $("div[name='" + emps + "']");
	        var graphData= [];
	        var l = 1;
	        var c = 0; //utilizada para obter a cor da legenda
	        var classe = ".main.l";
	        var className = "";
	        var keyIndex = 0;
	        var formatedKeys = $("#keys").val().replace('[','').replace(']','').split(',');
	        var months = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","x","z"];
	        employees.each(function(){
	        	var linhas = $(this).find("input[name='" + gData + "']");
				linhas.each(function(){
					var key = months[keyIndex] + "_" + formatedKeys[keyIndex];
					var value = $(this).val();
					
					graphData.push({
	                    "x": key,
	                    "y": ((value -1) +  1)
	                });
					keyIndex++;
				});
				keyIndex = 0;
				className = classe + l;
				data.main.push({
	                "className": className,
	                "data": graphData
	            });
				graphData = [];
				l++;
	        
				var empName = $(this).attr("empName");
				var color = "color" + c;
				$(chartLegendId).append("<span class='legend " + color + "'>" + empName + "</span>");
				c++;
	        });
	        
	        return data;
		}
		
		function populateChart(chartId, chartLegendId, emps, gData)
		{
			var myChart = new xChart('line-dotted', getData(chartLegendId, emps, gData), chartId, {
	        	axisPaddingTop: 5, 
	        	paddingLeft: 50,
	        	tickFormatX: function (x) { 
	        			var month = x.split("_")[1];
	        			return month; 
	        		},
        		tickFormatY: function (x) { 
        			return Math.round(x * 100) + "%"; 
        		},
	        	mouseover: function (d, i) {
	            	var pos = $(this).offset();
	            	$(tt).text(Math.round(d.y * 100) + "%").css({top: topOffset + pos.top, left: pos.left + leftOffset}).show();
	          	},
	          	mouseout: function (x) { 
	          		$(tt).hide(); 
	          	}
	        });
		}
		
		function populateChartMoney(chartId, chartLegendId, emps, gData)
		{
			var myChart = new xChart('line-dotted', getData(chartLegendId, emps, gData), chartId, {
	        	axisPaddingTop: 5, 
	        	paddingLeft: 50,
	        	tickFormatX: function (x) { 
	        			var month = x.split("_")[1];
	        			return month; 
	        		},
        		tickFormatY: function (x) { 
        			return "R$ " + x; 
        		},
	        	mouseover: function (d, i) {
	            	var pos = $(this).offset();
	            	$(tt).text("R$ " + d.y).css({top: topOffset + pos.top, left: pos.left + leftOffset}).show();
	          	},
	          	mouseout: function (x) { 
	          		$(tt).hide(); 
	          	}
	        });
		}
		
		function populateChartStatic(chartId, chartLegendId, emps, gData)
		{
			var myChart = new xChart('line-dotted', getData(chartLegendId, emps, gData), chartId, {
	        	axisPaddingTop: 5, 
	        	paddingLeft: 50,
	        	tickFormatX: function (x) { 
	        			var month = x.split("_")[1];
	        			return month; 
	        		},
        		tickFormatY: function (x) { 
        			return x; 
        		},
	        	mouseover: function (d, i) {
	            	var pos = $(this).offset();
	            	$(tt).text(d.y + " iten(s)").css({top: topOffset + pos.top, left: pos.left + leftOffset}).show();
	          	},
	          	mouseout: function (x) { 
	          		$(tt).hide(); 
	          	}
	        });
		}
		
		$("#tabRef2").click();
		$("#tabRef1").click();
	});
</script>
	</jsp:body>
</t:template>