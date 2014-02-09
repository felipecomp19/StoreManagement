<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
			<a href="${pageContext.request.contextPath}/clientType/create" title="<spring:message code="label.newClientType" />">
				<i class="icon-group"></i>
				<span><spring:message code="label.newClientType" /></span>
			</a> <span class="triangle-button blue"></span>
		</div>
	</div>
	<div class="row action-nav-row">
		<div class="col-sm-6 action-nav-button">
			<a href="${pageContext.request.contextPath}/mailingList/create" title="<spring:message code="label.newMailingList" />"> 
				<i class="icon-list-alt"></i><span><spring:message code="label.newMailingList" /></span>
			</a> <span class="label label-dark-red pull-right">5</span>
		</div>
		<div class="col-sm-6 action-nav-button">
			<a href="#" title="<spring:message code="label.newCampaign"/>"> 
				<i class="icon-envelope-alt"></i> <span><spring:message code="label.newCampaign"/></span>
			</a>
			<span class="triangle-button green"><span class="inner">+3</span></span>
		</div>
	</div>
	<div class="row action-nav-row">
		<div class="col-sm-6 action-nav-button">
			<a href="${pageContext.request.contextPath}/store/create" title="<spring:message code="label.newStore" />"> 
				<i class="icon-building"></i><span><spring:message code="label.newStore" /></span>
			</a> <span class="label label-dark-red pull-right">3</span>
		</div>
		<div class="col-sm-6 action-nav-button">
			<a href="#" title="<spring:message code="label.configurations"/>"> <i class="icon-wrench"></i>
				<span><spring:message code="label.configurations"/></span>
			</a> 
		</div>
	</div>
</div>