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
							<%-- <c:forEach var="store" items="${stores}">
								<div>
								<input type="checkbox" name="stores" value="${store.id}" id="st${store.id}" class="icheck">
								<label for="st${store.id}">${store.name}</label>
								</div>
							</c:forEach> --%>
							<!-- <div class="icheckbox_flat-aero checked" style="position: relative;"> -->
							<form:checkboxes id="stores" items="${stores}" path="stores" itemValue="idAsString" itemLabel="name" class="icheck"/>
							<!-- </div> -->
						</li>
						<li class="input">
							<input type="text" name="cpf" placeholder="<spring:message code="label.cpf"/>" value="${client.cpf}"/>
						</li>
						<li class="input">
							<input type="hidden" name="id" value="${client.id}"/>
							<input type="text" name="name" placeholder="<spring:message code="label.name"/>" value="${client.name}"/>
						</li>
						<li class="input">
							<input type="text" name="email" placeholder="<spring:message code="label.email"/>" value="${client.email}"/>
						</li>
						<li class="input">
							<label><spring:message code="label.selectClientType" /></label>
							<form:select path="clientType" items="${clientTypes}" itemValue="id" itemLabel="name" />
						</li>
					</ul>
				</div>
				<div class="col-lg-6">
					<ul class="padded separate-sections">
						<li><h4><spring:message code="label.address" /></h4></li>
						<li class="input">
							<input type="text" name="address.cep" placeholder="<spring:message code="label.cep"/>" value="${client.address.cep}"/>
						</li>
						<li class="input">
							<input type="text" name="address.street" placeholder="<spring:message code="label.street"/>" value="${client.address.street}"/>
						</li>
						<li class="input">
							<input type="text" name="address.number" placeholder="<spring:message code="label.number"/>" value="${client.address.number}"/>
						</li>
						<li class="input">
							<input type="text" name="address.city" placeholder="<spring:message code="label.city"/>" value="${client.address.city}"/>
						</li>
						<li class="input">
							<input type="text" name="address.state" placeholder="<spring:message code="label.state"/>" value="${client.address.state}"/>
						</li>
						<li class="input">
							<input type="text" name="address.neighbourhood" placeholder="<spring:message code="label.neighbourhood"/>" value="${client.address.neighbourhood}"/>
						</li>
						<li class="input">
							<input type="text" name="address.complement" placeholder="<spring:message code="label.complement"/>" value="${client.address.complement}"/>
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