<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container">
	<div class="row">
		<div class="area-top clearfix">
			<div class="pull-left header">
				<h3 class="title">
					<i class="icon-group"></i>
					<spring:message code="label.clients" />
				</h3>
				<h5>
					<span><spring:message code="subtitle.clients" /></span>
				</h5>
			</div>
			<ul class="list-inline pull-right sparkline-box">
				<li class="sparkline-row">
					<h4 class="blue">
						<span><spring:message code="label.clients" /></span>${clientsSize}
					</h4>
					<div class="sparkline big" data-color="blue">
						<%-- ${clientsCountChart} --%>
						<!--25,11,5,28,25,19,27,6,4,23,20,6-->
					</div>
				</li>
			</ul>
		</div>
	</div>
</div>