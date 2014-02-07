<!doctype html>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0">

	<!-- Always force latest IE rendering engine or request Chrome Frame -->
	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">

	<link rel="stylesheet"	href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,800">

	<!-- Use title if it's in the page YAML frontmatter -->
	<title>SM - Morana</title>

	<link href="${pageContext.request.contextPath}/resources/coreAdmin/stylesheets/application.css" media="screen" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/resources/coreAdmin/javascripts/application.js" type="text/javascript"></script>
</head>
<body>
	<!-- top bar -->
	<nav class="navbar navbar-default navbar-inverse navbar-static-top"	role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<a class="navbar-brand" href="#">SM - Morana</a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse-primary">
				<span class="sr-only">Toggle Side Navigation</span> <i
					class="icon-th-list"></i>
			</button>

			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse-top">
				<span class="sr-only">Toggle Top Navigation</span> <i
					class="icon-align-justify"></i>
			</button>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse navbar-collapse-top">
			<div class="navbar-right">
				<!-- user profile -->
				<ul class="nav navbar-nav navbar-left">
					<li class="dropdown">
						<a href="#"	class="dropdown-toggle dropdown-avatar" data-toggle="dropdown">
							<span> 
								<img class="menu-avatar" src="<%=request.getContextPath()%>/resources/coreAdmin/images/avatars/avatar1.jpg" />
								<span>John Smith <i class="icon-caret-down"></i></span> 
								<span class="badge badge-dark-red">5</span>
							</span>
						</a>
						<ul class="dropdown-menu">
							<!-- the first element is the one with the big avatar, add a with-image class to it -->
							<li class="with-image">
								<div class="avatar">
									<img
										src="<%=request.getContextPath()%>/resources/coreAdmin/images/avatars/avatar1.jpg" />
								</div> <span>John Smith</span>
							</li>
							<li class="divider"></li>
							<li><a href="#"><i class="icon-user"></i> <span>Profile</span></a></li>
							<li><a href="#"><i class="icon-cog"></i> <span>Settings</span></a></li>
							<li><a href="#"><i class="icon-envelope"></i> <span>Messages</span>
									<span class="label label-dark-red pull-right">5</span></a></li>
							<li><a href="#"><i class="icon-off"></i> <span>Logout</span></a></li>
						</ul>
					</li>
				</ul>
				<!-- [END] user profile -->
			</div>
		</div>
		<!-- /.navbar-collapse -->
	</nav>
	<!-- [end] top bar -->

	<!-- side bar -->
	<div class="sidebar-background">
		<div class="primary-sidebar-background"></div>
	</div>

	<div class="primary-sidebar">
		<!-- Main nav -->
		<ul class="nav navbar-collapse collapse navbar-collapse-primary">
			<li class="active">
				<span class="glow"></span>
				<a href="${pageContext.request.contextPath}/dashboard"><i class="icon-dashboard icon-2x"></i><span>Dashboard</span></a>
			</li>
			<!-- Clients menu -->
			<li class="dark-nav">
				<span class="glow"></span> 
				<a class="accordion-toggle collapsed " data-toggle="collapse" href="#yJ6h3Npe7C">
					 <i class="icon-group icon-2x"></i><span><spring:message code="label.clients" /><i class="icon-caret-down"></i></span>
				</a>

				<ul id="yJ6h3Npe7C" class="collapse ">
					<li class="">
						<a href="${pageContext.request.contextPath}/client/create"> <i	class="icon-plus-sign-alt"> </i><spring:message code="label.new" /> </a>
					</li>
					<li class="">
						<a href="${pageContext.request.contextPath}/client/list"> <i class="icon-th-large"></i> <spring:message code="label.listAll"/> </a>
					</li>
					<li class="">
						<a href="#"> <i class="icon-folder-open-alt"></i> <spring:message code="label.import" /> </a>
					</li>
					<li class="">
						<a href="${pageContext.request.contextPath}/clientType/list"> <i class="icon-user"></i> <spring:message code="label.clientTypes" /> </a>
					</li>
				</ul>
			</li>
			<!--  [END] Clients menu -->

			<!-- Lojas -->
			<li class="">
				<span class="glow"></span> 
				<a href="${pageContext.request.contextPath}/store/list"> <i class="icon-building icon-2x"></i> <span><spring:message code="label.stores"></spring:message></span> </a>
			</li>
			<!-- [END] Lojas -->
				
			<!-- Campanhas -->
			<li class="dark-nav">
				<span class="glow"></span> 
				<a class="accordion-toggle collapsed " data-toggle="collapse" href="#campaigns">
					 <i class="icon-envelope-alt icon-2x"></i><span><spring:message code="label.campaigns" /><i class="icon-caret-down"></i></span>
				</a>
				<ul id="campaigns" class="collapse ">
					<li>
						<a href="${pageContext.request.contextPath}/campaign/create"> <i class="icon-plus-sign-alt"> </i><spring:message code="label.newCampaign" /> </a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/campaign/list"> <i class="icon-th-large"> </i><spring:message code="label.listAllCampaigns" /> </a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/mailingList/list"> <i class="icon-list-alt"> </i><spring:message code="label.mailingLists" /> </a>
					</li>
				</ul>
			</li>
			<!-- [END] Campanhas -->

			<!-- Configurações  -->
			<li class="dark-nav ">
				<span class="glow"></span> 
				<a class="accordion-toggle collapsed " data-toggle="collapse" href="#WLGsdJPav9">
					<i class="icon-wrench icon-2x"></i> <span> <spring:message code="label.configurations" /> <i class="icon-caret-down"></i></span>
				</a>

				<ul id="WLGsdJPav9" class="collapse ">
					<li class="">
						<a href="#"> <i class="icon-user"></i> <spring:message code="label.users"></spring:message></a>
					</li>
				</ul>
			</li>
			<!-- [END] Configurações -->
		</ul>

		<div class="hidden-sm hidden-xs">
			<!-- 
			<div class="text-center" style="margin-top: 60px">
				<div class="easy-pie-chart-percent" style="display: inline-block"
					data-percent="89">
					<span>89%</span>
				</div>
				<div style="padding-top: 20px">
					<b>CPU Usage</b>
				</div>
			</div>
			 -->

			<hr class="divider" style="margin-top: 500px">

			<div class="sparkline-box side">

				<div class="sparkline-row">
					<h4 class="gray">
						<span><spring:message code="label.clients"/></span> 847
					</h4>
					<div class="sparkline big" data-color="gray">
						<!--16,7,23,13,12,11,15,4,19,18,4,24-->
					</div>
				</div>

				<hr class="divider">
				<div class="sparkline-row">
					<h4 class="blue">
						<span><spring:message code="label.sales"></spring:message></span> 223
					</h4>
					<div class="sparkline big" data-color="blue">
						<!--11,19,20,20,5,18,11,6,16,20,26,11-->
					</div>
				</div>

				<hr class="divider">
				<div class="sparkline-row">
					<h4 class="dark-green">
						<span><spring:message code="label.income" /></span> $43.330
					</h4>
					<div class="sparkline big" data-color="darkGreen">
						<!--6,3,24,25,27,29,14,26,20,8,12,20-->
					</div>
				</div>

				<hr class="divider">
			</div>
		</div>
	</div>
	<!--[END] side bar  -->
	
	<div class="main-content">
		<jsp:doBody />
	</div>
</body>
</html>