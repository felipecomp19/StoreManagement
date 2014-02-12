<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-md-6">
		<form:form class="fill-up" method="POST" commandname="store" action="${pageContext.request.contextPath}/store/save" >
			<div class="row">
				<div class="col-lg-6">
					<ul class="padded separate-sections">
						<li class="input">
							<input type="hidden" name="id" value="${store.id}"/>
							<label><spring:message code="label.name" /></label>
							<input type="text" name="name" placeholder="<spring:message code="label.name"/>" value="${store.name}"/>
						</li>
						<li class="input">
							<label><spring:message code="label.description" /></label>
							<input type="text" name="description" placeholder="<spring:message code="label.description"/>" value="${store.description}"/>
						</li>
						<li class="input">
							<label><spring:message code="label.telephone" /></label>
							<input type="text" name="telephone" placeholder="<spring:message code="label.telephone"/>" value="${store.telephone}"/>
						</li>
					</ul>
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-blue"><spring:message code="label.save" /></button>
				<a href="${pageContext.request.contextPath}/store/list">
					<button type="button" class="btn btn-default"><spring:message code="label.cancel" /></button>
				</a>
			</div>
		</form:form>
	</div>
</div>