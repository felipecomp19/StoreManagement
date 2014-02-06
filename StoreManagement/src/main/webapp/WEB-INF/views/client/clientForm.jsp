<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-6">
		<form:form class="fill-up" method="POST" modelAttribute="client" commandname="client" action="${pageContext.request.contextPath}/client/save" >
			<div class="row">
				<div class="col-lg-6">
					<ul class="padded separate-sections">
						<li>
							<div class="icheckbox_flat-aero checked" style="position: relative;">
								<form:checkboxes id="stores" items="${stores}" path="stores" itemValue="idAsString" itemLabel="name" class="icheck" style="position: absolute; opacity: 0;"/>
							</div>
						</li>
						<li class="input">
							<input type="hidden" name="id" value="${client.id}"/>
							<input type="text" name="name" placeholder="<spring:message code="label.name"/>" value="${client.name}"/>
						</li>
						<li class="input">
							<input type="text" name="cpf" placeholder="<spring:message code="label.cpf"/>" value="${client.cpf}"/>
						</li>
						<li class="input">
							<input type="text" name="email" placeholder="<spring:message code="label.email"/>" value="${client.email}"/>
						</li>
						<li class="input">
							<form:select path="clientType" items="${clientTypes}" itemValue="id" itemLabel="name" />
						</li>
					</ul>
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-blue"><spring:message code="label.save" /></button>
				<a href="${pageContext.request.contextPath}/client/list">
					<button type="button" class="btn btn-default"><spring:message code="label.cancel" /></button>
				</a>
			</div>
		</form:form>
	</div>
</div>