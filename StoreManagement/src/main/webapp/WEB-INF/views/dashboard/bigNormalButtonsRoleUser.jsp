<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<div class="action-nav-normal">
	<div class="row action-nav-row">
		<div class="col-sm-6 action-nav-button">
			<a href="${pageContext.request.contextPath}/client/create" title="<spring:message code="label.newClient" />"> 
				<i class="icon-user"></i>
				<span><spring:message code="label.newClient" /></span>
			</a> 
			<span class="triangle-button green"><i class="icon-plus"></i></span>
		</div>
		<div class="col-sm-6 action-nav-button">
			<a href="#" title="<spring:message code="label.configurations"/>"> <i class="icon-wrench"></i>
				<span><spring:message code="label.configurations"/></span>
			</a> 
		</div>
	</div>
	<div class="row action-nav-row">
	</div>
	<div class="row action-nav-row">
	</div>
</div>