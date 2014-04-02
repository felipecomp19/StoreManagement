<!doctype html>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/cleditor/jquery.cleditor.css" />
	
	
	<script src="${pageContext.request.contextPath}/resources/coreAdmin/javascripts/application.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.mask.min.js" type="text/javascript" ></script>
 	<script src="${pageContext.request.contextPath}/resources/cleditor/jquery.cleditor.min.js" type="text/javascript"></script>
<!--  	<script src="http://cidades-estados-js.googlecode.com/files/cidades-estados-1.2-utf8.js" type="text/javascript"></script> --> 
 	<script src="${pageContext.request.contextPath}/resources/js/jquery.blockUI.js" type="text/javascript"></script>
 	<script src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js" type="text/javascript"></script>
 	<script src="${pageContext.request.contextPath}/resources/js/jquery.maskMoney.min.js" type="text/javascript"></script>
 	
 	<!-- jqGrid -->
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/jquery-ui/css/smoothness/jquery-ui.min.css" />
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/jqGrid/css/ui.jqgrid.css" />
 	
 	<script src="${pageContext.request.contextPath}/resources/js/grid.locale-pt-br.js" type="text/javascript"></script>
 	<script src="${pageContext.request.contextPath}/resources/jqGrid/js/jquery.jqGrid.src.js" type="text/javascript"></script>
 	
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
				
				<%-- <ul class="nav navbar-nav navbar-left">
					<li>
						<c:url value="/j_spring_security_logout" var="logoutUrl" />
						<a href="${logoutUrl}"><spring:message code="label.logout"/></a>
					</li>
				</ul> --%>
				<!-- user profile -->
				<ul class="nav navbar-nav navbar-left">
					<li class="dropdown">
						<a href="#"	class="dropdown-toggle dropdown-avatar" data-toggle="dropdown">
							<span> 
								<i class="icon-user icon-2x"></i>
								<span>${loggedUser.name} <i class="icon-caret-down"></i></span> 
							</span>
						</a>
						
						<ul class="dropdown-menu" style="width: 165px;">
							<!-- the first element is the one with the big avatar, add a with-image class to it -->
							<%-- <li class="with-image">
								<div class="avatar">
									<i class="icon-user icon-2x"></i>
								</div> <span>${loggedUser.name}</span>
							</li>
							<li class="divider"></li> --%>
							<li>
								<a href="${pageContext.request.contextPath}/user/editProfile/${loggedUser.id}">
									<i class="icon-cog"></i><span><spring:message code="label.configurations" /></span></a>
							</li>
							<li class="divider"></li>
							<li>
								<c:url value="/j_spring_security_logout" var="logoutUrl" />
								<a href="${logoutUrl}"><i class="icon-off"></i><span><spring:message code="label.logout"/></span></a>
							</li>
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
						<a href="${pageContext.request.contextPath}/client/list"> <i class="icon-th-large"></i> <spring:message code="label.listAll"/> </a>
					</li>
					<!-- 
					<li class="">
						<a href="${pageContext.request.contextPath}/client/upload"> <i class="icon-folder-open-alt"></i> <spring:message code="label.import" /> </a>
					</li>
					 -->
					<sec:authorize ifAnyGranted="ROLE_MANAGER,ROLE_ADMIN">
						<li class="">
							<a href="${pageContext.request.contextPath}/clientType/list"> <i class="icon-user"></i> <spring:message code="label.clientTypes" /> </a>
						</li>
					</sec:authorize>
				</ul>
			</li>
			<!--  [END] Clients menu -->

			<!-- Lojas -->
			<sec:authorize ifAnyGranted="ROLE_ADMIN">
				<li class="">
					<span class="glow"></span> 
					<a href="${pageContext.request.contextPath}/store/list"> <i class="icon-building icon-2x"></i> <span><spring:message code="label.stores"></spring:message></span> </a>
				</li>
			</sec:authorize>
			<!-- [END] Lojas -->
				
			<!-- Campanhas -->
			<sec:authorize ifAnyGranted="ROLE_MANAGER,ROLE_ADMIN">
				<!-- 
				<li class="">
					<span class="glow"></span> 
					<a href="${pageContext.request.contextPath}/campaign/bulding"> 
						<i class="icon-envelope-alt icon-2x"></i><span><spring:message code="label.campaigns" /><i class="icon-caret-down"></i></span> 
					</a>
				</li>
				 -->
				<!-- -->
				<li class="dark-nav">
					<span class="glow"></span> 
					<a class="accordion-toggle collapsed " data-toggle="collapse" href="#campaigns">
						 <i class="icon-envelope-alt icon-2x"></i><span><spring:message code="label.campaigns" /><i class="icon-caret-down"></i></span>
					</a>
					<ul id="campaigns" class="collapse ">
						<li>
							<a href="${pageContext.request.contextPath}/campaign/list"> <i class="icon-th-large"> </i><spring:message code="label.listAllCampaigns" /> </a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/mailingList/list"> <i class="icon-list-alt"> </i><spring:message code="label.mailingLists" /> </a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/imagens/list"> <i class="icon-picture"> </i><spring:message code="label.imagens" /> </a>
						</li>
					</ul>
				</li>
				 
			</sec:authorize>
			<!-- [END] Campanhas -->
			
			<!-- 
			<li class="">
				<span class="glow"></span> 
				<a href="${pageContext.request.contextPath}/calendar"> <i class="icon-calendar icon-2x"></i> <span><spring:message code="label.calendar"></spring:message></span> </a>
			</li>
			 -->
			<sec:authorize ifAnyGranted="ROLE_MANAGER,ROLE_ADMIN">
				<li class="">
					<span class="glow"></span> 
					<a href="${pageContext.request.contextPath}/employee/list"> <i class="icon-sitemap icon-2x"></i> <span><spring:message code="label.employees" /></span> </a>
				</li>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="">
					<span class="glow"></span> 
					<a href="${pageContext.request.contextPath}/indicator/list"> <i class="icon-tasks icon-2x"></i> <span><spring:message code="label.indicators" /></span> </a>
				</li>
				<li class="">
					<span class="glow"></span> 
					<a href="${pageContext.request.contextPath}/report/"><i class="icon-book icon-2x"></i> <span><spring:message code="label.reports" /></span> </a>
				</li>  
			</sec:authorize>
			 
			<!-- Configurações  -->
			<sec:authorize ifAnyGranted="ROLE_MANAGER,ROLE_ADMIN">
				<li class="dark-nav ">
					<span class="glow"></span> 
					<a class="accordion-toggle collapsed " data-toggle="collapse" href="#WLGsdJPav9">
						<i class="icon-wrench icon-2x"></i> <span> <spring:message code="label.configurations" /> <i class="icon-caret-down"></i></span>
					</a>
	
					<ul id="WLGsdJPav9" class="collapse ">
						<li class="">
							<a href="${pageContext.request.contextPath}/user/list"> <i class="icon-user"></i> <spring:message code="label.users"></spring:message></a>
						</li>
					</ul>
				</li>
			</sec:authorize>
			<!-- [END] Configurações -->
		</ul>
	</div>
	<!--[END] side bar  -->
	
	<div class="main-content">
		<jsp:doBody />
	</div>
</body>
</html>
