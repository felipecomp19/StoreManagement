<!doctype html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<meta name="viewport"
	content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,800">


<meta charset="utf-8">

<!-- Always force latest IE rendering engine or request Chrome Frame -->
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">

<!-- Use title if it's in the page YAML frontmatter -->
<title>SM - Morana</title>

<link
	href="${pageContext.request.contextPath}/resources/coreAdmin/stylesheets/application.css"
	media="screen" rel="stylesheet" type="text/css" />
<script
	src="${pageContext.request.contextPath}/resources/coreAdmin/javascripts/application.js"
	type="text/javascript"></script>

<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>

<body>
	<nav class="navbar navbar-default navbar-inverse navbar-static-top" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<a class="navbar-brand" href="#">SM - Morana</a>
		</div>
	</nav>
	
	<div class="container">
		<div class="col-md-4 col-md-offset-4">
			<div class="padded">
				<div class="login box" style="margin-top: 80px;">
					<div class="box-header">
						<span class="title">Login</span>
					</div>
					<div class="box-content padded">
						<c:if test="${not empty error}">
							<div class="errorblock">
								Your login attempt was not successful, try again.<br /> Caused
								: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
							</div>
						</c:if>
						<form class="separate-sections" name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
							<div class="input-group addon-left">
								<span class="input-group-addon" href="#">
									<i class="icon-user"></i>
								</span> 
								<input type="text" name='j_username' placeholder="username">
							</div>
							<div class="input-group addon-left">
								<span class="input-group-addon" href="#"> <i class="icon-key"></i>
								</span> 
								<input type="password" name='j_password' placeholder="password">
							</div>

							<div>
								<button class="btn btn-blue btn-block" type="submit">Login<i class="icon-signin"></i></button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
