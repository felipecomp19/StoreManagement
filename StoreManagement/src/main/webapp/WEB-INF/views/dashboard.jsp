<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
							<span> A subtitle can go here </span>
						</h5>
					</div>
					<ul class="list-inline pull-right sparkline-box">
						<li class="sparkline-row">
							<h4 class="blue">
								<span><spring:message code="label.clients"/></span> 847
							</h4>
							<div class="sparkline big" data-color="blue">
								<!--25,11,5,28,25,19,27,6,4,23,20,6-->
							</div>
						</li>
						<li class="sparkline-row">
							<h4 class="green">
								<span><spring:message code="label.income" /></span> $43.330
							</h4>
							<div class="sparkline big" data-color="green">
								<!--28,26,13,18,8,6,24,19,3,6,19,6-->
							</div>
						</li>
						<li class="sparkline-row">
							<h4 class="red">
								<span><spring:message code="label.sales" /></span> 7930
							</h4>
							<div class="sparkline big">
								<!--16,23,28,8,12,9,25,11,16,16,17,13-->
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
				<div class="col-md-3">
					<!--big normal buttons-->
					<div class="action-nav-normal">
	
						<div class="row action-nav-row">
							<div class="col-sm-6 action-nav-button">
								<a href="#" title="New Project"> <i class="icon-file-alt"></i>
									<span>New Project</span>
								</a> <span class="triangle-button red"><i class="icon-plus"></i></span>
							</div>
							<div class="col-sm-6 action-nav-button">
								<a href="#" title="Messages"> <i class="icon-comments-alt"></i>
									<span>Messages</span>
								</a> <span class="label label-black">14</span>
							</div>
						</div>
						<div class="row action-nav-row">
							<div class="col-sm-6 action-nav-button">
								<a href="#" title="Files"> <i class="icon-folder-open-alt"></i>
									<span>Files</span>
								</a>
							</div>
							<div class="col-sm-6 action-nav-button">
								<a href="#" title="Users"> <i class="icon-user"></i> <span>Users</span>
								</a> <span class="triangle-button green"><span class="inner">+3</span></span>
							</div>
						</div>
						<div class="row action-nav-row">
							<div class="col-sm-6 action-nav-button">
								<a href="#" title="Friends"> <i class="icon-facebook-sign"></i>
									<span>Friends</span>
								</a>
							</div>
							<div class="col-sm-6 action-nav-button">
								<a href="#" title="Followers"> <i class="icon-twitter"></i>
									<span>Followers</span>
								</a> <span class="triangle-button blue"><span class="inner">+8</span></span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- [END] main content -->
		
	</jsp:body>
</t:template>
</html>