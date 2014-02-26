<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box-content padded">
	<div class="row">
		<div class="col-md-4 separate-sections" style="margin-top: 5px;">
			<div class="row">
				<div class="col-md-12">
					<div class="dashboard-stats">
						<ul class="list-inline">
							<li class="glyph"><i class="icon-group icon-2x"></i></li>
							<li class="count">${clientsSize}</li>
						</ul>

						<div class="progress">
							<div class="progress-bar progress-blue tip" title="100%"
								data-percent="100"></div>
						</div>

						<div class="stats-label"><spring:message code="label.totalOfClients"/></div>
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 30px;">
				<% int countSt = 1; %>				
				<c:forEach var="store" items="${stores}" >
					<% if(countSt <= 2) { %>
						<div class="col-md-6">
							<div class="dashboard-stats small">
								<ul class="list-inline">
									<li class="glyph"><i class="icon-building"></i></li>
									<li class="count">${store.clientsSize}</li>
								</ul>
								<div class="progress">
									<div class="progress-bar progress-blue tip" title="${(store.clientsSize/clientsSize)*100}%" data-percent="${(store.clientsSize/clientsSize)*100}"> </div>
								</div>
								<div class="stats-label">${store.nameWithDesc}</div>
							</div>
						</div>
					<%}else{ %>
						<div class="col-md-6" hidden="true">
							<div class="dashboard-stats small">
								<ul class="list-inline">
									<li class="glyph"><i class="icon-building"></i></li>
									<li class="count">${store.clientsSize}</li>
								</ul>
								<div class="progress">
									<div class="progress-bar progress-blue tip" title="80%"
										data-percent="80"></div>
								</div>
								<div class="stats-label">${store.nameWithDesc}</div>
							</div>
						</div>	
					<%} countSt++;%>
				</c:forEach>
			</div>

		</div>
		<div class="col-md-8">
			<!-- <div class="sine-chart" id="xchart-sine"></div> -->
			<div class="sine-chart" id="storeChart"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var data = [{
            "xScale":"ordinal",
            "comp":[],
            "main":[
                {
                    "className":".main.l1",
                    "data":[
                        {"y":12,"x":"2012-11-19T00:00:00"},
                        {"y":18,"x":"2012-11-20T00:00:00"},
                        {"y":8,"x":"2012-11-21T00:00:00"},
                        {"y":7,"x":"2012-11-22T00:00:00"},
                        {"y":6,"x":"2012-11-23T00:00:00"},
                        {"y":12,"x":"2012-11-24T00:00:00"},
                        {"y":8,"x":"2012-11-25T00:00:00"}
                    ]
                }
            ],
            "type":"bar",
            "yScale":"linear"
        },{
            "xScale":"ordinal",
            "comp":[],
            "main":[
                {
                    "className":".main.l1",
                    "data":[
                        {"y":12,"x":"2012-11-19T00:00:00"},
                        {"y":18,"x":"2012-11-20T00:00:00"},
                        {"y":8,"x":"2012-11-21T00:00:00"},
                        {"y":7,"x":"2012-11-22T00:00:00"},
                        {"y":6,"x":"2012-11-23T00:00:00"},
                        {"y":12,"x":"2012-11-24T00:00:00"},
                        {"y":8,"x":"2012-11-25T00:00:00"}
                    ]
                },{
                    "className":".main.l2",
                    "data":[
                        {"y":29,"x":"2012-11-19T00:00:00"},
                        {"y":33,"x":"2012-11-20T00:00:00"},
                        {"y":13,"x":"2012-11-21T00:00:00"},
                        {"y":16,"x":"2012-11-22T00:00:00"},
                        {"y":7,"x":"2012-11-23T00:00:00"},
                        {"y":18,"x":"2012-11-24T00:00:00"},
                        {"y":8,"x":"2012-11-25T00:00:00"}
                    ]
                }
            ],
            "type":"cumulative",
            "yScale":"linear"
        }];
		
		var order = [0, 1],
        i = 0,
        xFormat = d3.time.format('%A'),
        rotateTimer,
        chart,
        t = 3500;
		
		if ($("#storeChart").length > 0) {
	        chart = new xChart('bar', data[order[i]], '#storeChart', {
	            axisPaddingTop: 5,
	            paddingLeft: 30,
	            dataFormatX: function (x) { return new Date(x); },
	            tickFormatX: function (x) { return d3.time.format('%a')(x); }
	        });

	        rotateTimer = setTimeout(rotateChart, t);
	    }

	    function updateChart(i) {
	        chart.setData(data[i]);
	    }

	    function rotateChart() {
	        i += 1;
	        i = (i >= order.length) ? 0 : i;
	        updateChart(order[i]);
	        rotateTimer = setTimeout(rotateChart, t);
	    }
	});
</script>