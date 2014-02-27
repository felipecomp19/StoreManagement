<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<t:template>
	<jsp:body>
		
		<!-- top main content -->
		<div class="container">
			<div class="row">
				<div class="area-top clearfix">
					<div class="pull-left header">
						<h3 class="title">
							<i class="icon-dashboard"></i> Dashboard
						</h3>
						<h5>
							<span><spring:message code="subtitle.dashboard"/></span>
						</h5>
					</div>
					<ul class="list-inline pull-right sparkline-box">
						<li class="sparkline-row">
							<h4 class="blue">
								<span><spring:message code="label.clients"/></span>${clientsSize}
							</h4>
							<div class="sparkline big" data-color="blue">
								<%-- ${clientsCountChart } --%>
								<!--25,11,5,28,25,19,27,6,4,23,20,6-->
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- [END] top main content -->
		
		<!-- Breadcrumb -->		
		<div class="container padded">
			<div class="row">
				<div id="breadcrumbs">
					<div class="breadcrumb-button blue">
						<span class="breadcrumb-label"><i class="icon-home"></i>
							Home</span> <span class="breadcrumb-arrow"><span></span></span>
					</div>
					<div class="breadcrumb-button">
						<span class="breadcrumb-label"> <i class="icon-dashboard"></i>
							Dashboard
						</span> <span class="breadcrumb-arrow"><span></span></span>
					</div>
				</div>
			</div>
		</div>
		<!-- [END] Breadcrumb -->
		
		<!-- main content -->
		<div class="container">
	   		<div class="row">
				<!--big normal buttons-->
				<div class="col-md-3">
					<%@include file="bigNormalButtons.jsp" %>
				</div>
				<!--[END] big normal buttons-->
				
				<!-- charts -->				
				<div class="col-md-9">
					<div class="box">
						<div class="box-header">
			        		<div class="title">
			        			<spring:message code="label.charts"/>
		        			</div>
		      			</div>
		      			<div class="box-content">
		      				<%@include file="charts.jsp" %>
		      			</div>
					</div>
				</div>
				<!-- [END] charts -->
			</div>
			
			<%-- <div class="row">
			  <!-- calendar -->
			  <div class="col-md-6">
			    <div class="box">
			      <div class="box-header">
			        <div class="title"><spring:message code="label.calendar"/></div>
			      </div>
			      <div class="box-content">
			        <div id="calendar"></div>
			      </div>
			    </div>
			  </div>
			  <!-- [END] calendar -->
			  
			  <!-- activities -->
			  <div class="col-md-6">
			    <div class="box">
			      <div class="box-header">
			        <div class="title"><spring:message code="label.lastUpdates"/></div>
			      </div>
			      <div class="box-content">
			      	<%@include file="activities.jsp" %>
			      </div>
			    </div>
			  </div>
			  <!-- [END] activities -->
  			</div> --%>
		</div>
		<!-- [END] main content -->
	</jsp:body>
</t:template>
</html>