<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-md-4">
		<form:form class="fill-up validatable" method="POST" commandname="store" action="${pageContext.request.contextPath}/store/save" >
			<div class="row">
				<div class="col-md-12">
					<ul class="padded separate-sections">
						<li class="input">
							<input type="hidden" name="id" value="${store.id}"/>
							<label><spring:message code="label.name" /></label>
							<input type="text" name="name" class="validate[required]" placeholder="<spring:message code="label.name"/>" value="${store.name}"/>
						</li>
						<li class="input">
							<label><spring:message code="label.description" /></label>
							<input type="text" name="description" placeholder="<spring:message code="label.description"/>" value="${store.description}"/>
						</li>
						<li class="input">
							<label><spring:message code="label.telephone" /></label>
							<input type="text" id="telephone" name="telephone" placeholder="<spring:message code="label.telephone"/>" value="${store.telephone}"/>
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
	<div class="col-md-8">
		<ul class="padded separate-sections">
			<li>
				<div class="box">
					<div class="box-header">
				    	<span class="title"><spring:message code="label.clients" /></span>
			  		</div>
			  		<div class="box-content">
			  			<div id="dataTables">
		  				<table class="dTable responsive">
				  			<thead>
								<tr>
									<td><spring:message code="label.name" /></td>
									<td><spring:message code="label.email" /></td>
									<td><spring:message code="label.clientType" /></td>
									<td><spring:message code="label.birthday" /></td>
									<td><spring:message code="label.clientSince" /></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="client" items="${store.clients}" >
									<tr>
										<td>${client.name}</td>
										<td>${client.email}</td>
										<td>${client.clientType.name}</td>
										<td>${client.formatedBirthday}</td>
										<td>${client.formatedCreatedOn}</td>
									</tr>
								</c:forEach>
							</tbody>
				  		</table>
				  		</div>
			  		</div>
  				</div>
			</li>
		</ul>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function () {
    	$("#telephone").mask("(99) 9999-99999");
	});
</script>