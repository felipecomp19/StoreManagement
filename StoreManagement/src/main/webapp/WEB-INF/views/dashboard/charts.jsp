<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-content padded">
	<div class="row">
		<div class="col-md-12">
			<div class="sine-chart" id="storeChart">
				<c:forEach items="${totalClientsByMontList}" var="tcbm">
					<div name="storeLine" storeName="${tcbm.storeNameWithDesc}">
						<input type="hidden" name="storeChartData" month="01.<spring:message code="month.jan" />" count="${tcbm.countClientsJan}" />
						<input type="hidden" name="storeChartData" month="02.<spring:message code="month.feb" />" count="${tcbm.countClientsFeb}" />
						<input type="hidden" name="storeChartData" month="03.<spring:message code="month.mar" />" count="${tcbm.countClientsMar}" />
						<input type="hidden" name="storeChartData" month="04.<spring:message code="month.apr" />" count="${tcbm.countClientsApr}" />
						<input type="hidden" name="storeChartData" month="05.<spring:message code="month.may" />" count="${tcbm.countClientsMay}" />
						<input type="hidden" name="storeChartData" month="06.<spring:message code="month.jun" />" count="${tcbm.countClientsJun}" />
						<input type="hidden" name="storeChartData" month="07.<spring:message code="month.jul" />" count="${tcbm.countClientsJul}" />
						<input type="hidden" name="storeChartData" month="08.<spring:message code="month.ago" />" count="${tcbm.countClientsAug}" />
						<input type="hidden" name="storeChartData" month="09.<spring:message code="month.set" />" count="${tcbm.countClientsSet}" />
						<input type="hidden" name="storeChartData" month="10.<spring:message code="month.oct" />" count="${tcbm.countClientsOct}" />
						<input type="hidden" name="storeChartData" month="11.<spring:message code="month.nov" />" count="${tcbm.countClientsNov}" />
						<input type="hidden" name="storeChartData" month="12.<spring:message code="month.dez" />" count="${tcbm.countClientsDez}" />
					</div>
				</c:forEach>
			</div>
			<div id="storeChart_legend" style="text-align: center;">
				<!-- js will populate with legend based on the chart -->
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		
		var tt = document.createElement('div'),leftOffset = -(~~$('html').css('padding-left').replace('px', '') + ~~$('body').css('margin-left').replace('px', '')),topOffset = -32;
		tt.className = 'ex-tooltip';
		document.body.appendChild(tt);
		
		if($("#storeChart").length > 0) {
	        var myChart = new xChart('line-dotted', getData(), '#storeChart', {
	        	axisPaddingTop: 5, 
	        	paddingLeft: 50,
	        	tickFormatX: function (x) { 
	        			var month = x.split(".")[1];
	        			return month; 
	        		},
	        	mouseover: function (d, i) {
	            	var pos = $(this).offset();
	            	$(tt).text(d.y).css({top: topOffset + pos.top, left: pos.left + leftOffset}).show();
	          	},
	          	mouseout: function (x) { $(tt).hide(); }
	        });
	    }
		
		function getData(){
	        var data = {
	        	 "xScale":"ordinal",
	             "comp":[],
	             "yScale":"linear",
	             "type":"cumulative"
	        };

	        data.main = [];
	        
	        var stores = $("div[name='storeLine']");
	        var graphData= [];
	        var l = 1;
	        var c = 0; //utilizada para obter a cor da legenda
	        var classe = ".main.l";
	        var className = "";
	        stores.each(function(){
				var linhas = $(this).find("input[name='storeChartData']");
				linhas.each(function(){
					graphData.push({
	                    "x": $(this).attr("month"),
	                    "y": (($(this).attr("count") - 1) + 1)
	                });	
				});
				className = classe + l;
				data.main.push({
	                "className": className,
	                "data": graphData
	            });
				graphData = [];
				l++;
	        
				var storeName = $(this).attr("storeName");
				var color = "color" + c;
				$("#storeChart_legend").append("<span class='legend " + color + "'>" + storeName + "</span>");
				c++;
	        });
	        
	        
	        return data;
		}
	});
</script>