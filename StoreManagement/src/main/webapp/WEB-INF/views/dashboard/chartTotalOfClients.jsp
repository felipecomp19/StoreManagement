<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-content padded">
	<div class="row">
		<div class="col-md-12">
			<div class="xcharts-clientsByStore" style="width: 100%; height:200px">
				<c:forEach items="${stores}" var="store">
					<input type="hidden" name="storeData" storeName="${store.nameWithDesc}" clientSize="${store.clientsSize}">
					
				</c:forEach>
				
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var tt = document.createElement('div'),leftOffset = -(~~$('html').css('padding-left').replace('px', '') + ~~$('body').css('margin-left').replace('px', '')),topOffset = -32;
		tt.className = 'ex-tooltip';
		document.body.appendChild(tt);
		
		if($(".xcharts-clientsByStore").length > 0) {
	        var myChart = new xChart('bar', getData(), '.xcharts-clientsByStore', {
	        	axisPaddingTop: 5, 
	        	paddingLeft: 35,
	        	mouseover: function (d, i) {
	            	var pos = $(this).offset();
	            	$(tt).text(d.y).css({top: topOffset + pos.top, left: pos.left + leftOffset}).show();
	          	},
	          	mouseout: function (x) { $(tt).hide(); }
        	});
	        /* $("#randomize-bar-chart").click(function(){
	            myChart.setData(getData());
	        }); */
	    }
		
		function getData(){
	        var data = {
	            "xScale": "ordinal",
	            "yScale": "linear"
	        };

	        data.main = [];
	        
	        var stores = $("input[name='storeData']");
	        var graphData= [];
	        
	        stores.each(function(){
	        	var valy = ($(this).attr("clientSize") -1) + 1;
                graphData.push({
                    "x": $(this).attr("storeName"),
                    "y": valy
                });
            });

            data.main.push({
                "className": ".food",
                "data": graphData
            });
	        
	        return data;
		}
	});
</script>