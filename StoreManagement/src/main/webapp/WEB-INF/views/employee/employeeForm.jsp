<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-md-12">
		<form:form class="fill-up validatable" method="POST" commandName="employee" modelAttribute="employee" action="${pageContext.request.contextPath}/employee/save" >
			<div class="row">
				<div class="col-md-3">
					<ul class="padded separate-sections">
						<li><h4><spring:message code="label.stores"/></h4></li>
						<li>
							<div id="storesCB">
								<ul  class="padded separate-sections">
									<form:radiobuttons id="store" items="${stores}" path="store" itemValue="idAsString" itemLabel="nameWithDesc" class="icheck break-word validate[required]" element="li" />
								</ul>
							</div>
						</li>
					</ul>
				</div>
				<div class="col-md-4">
					<ul class="padded separate-sections">
						<li>
							<h4><spring:message code="label.employeeData" /></h4>
						</li>
						<li class="input">
							<input type="hidden" name="id" value="${employee.id}"/>
							<label><spring:message code="label.name" /></label>
							<input type="text" name="name" class="validate[required]" placeholder="<spring:message code="label.name"/>" value="${employee.name}"/>
						</li>
					</ul>
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-blue"><spring:message code="label.save" /></button>
				<a href="${pageContext.request.contextPath}/employee/list">
					<button type="button" class="btn btn-default"><spring:message code="label.cancel" /></button>
				</a>
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function () {
		$("#storesCB").find("span").css("padding-right","25px");
	});
</script>