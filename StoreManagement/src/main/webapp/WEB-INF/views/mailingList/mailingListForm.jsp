<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-12">
		<div class="row">
			<form:form class="fill-up" method="POST" modelAttribute="mlForm" commandname="mlForm" action="${pageContext.request.contextPath}/mailingList/save">
				<div class="col-md-4">
					<ul class="padded separate-sections">
						<li>
							<h4>
								<spring:message code="label.createYourNewList" />
							</h4>
						</li>
						<li class="input">
							<input type="hidden" name="mailingList.id" value="${mlForm.mailingList.id}" /> 
							<input type="hidden" name="mailingList.createdOn" value="${mlForm.mailingList.createdOn}" />
							<label><spring:message code="label.listName" /></label> 
							<input type="text" name="mailingList.name" placeholder="<spring:message code="label.listName"/>" value="${mlForm.mailingList.name}" /></li>
						<li class="input">
							<label><spring:message code="label.defaultFromName" /></label> 
							<input type="text" name="mailingList.defaultFromName" placeholder="<spring:message code="label.defaultFromName"/>"
							value="${mlForm.mailingList.defaultFromName}" /></li>
						<li class="input">
							<label><spring:message code="label.defaultFromEmail" /></label> 
							<input type="text" name="mailingList.defaultFromEmail" placeholder="<spring:message code="label.defaultFromEmail"/>" value="${mlForm.mailingList.defaultFromEmail}" /></li>
						<li class="input">
							<label><spring:message code="label.defaultSubject" /></label> 
							<input type="text" name="mailingList.defaultSubject" placeholder="<spring:message code="label.defaultSubject"/>" value="${mlForm.mailingList.defaultSubject}" /></li>
					</ul>
					<div class="form-actions">
						<button type="submit" class="btn btn-blue">
							<spring:message code="label.save" />
						</button>
						<a href="${pageContext.request.contextPath}/mailingList/list">
							<button type="button" class="btn btn-default">
								<spring:message code="label.cancel" />
							</button>
						</a>
					</div>
				</div>
				<div class="col-md-8">
					<ul class="padded separate-sections">
						<li>
							<div class="box">
								<div class="box-header">
							    	<span class="title"><spring:message code="label.selectClients" /></span>
							    	<ul class="box-toolbar">
							      	<li><span class="label label-green">Filtros</span></li>
							    	</ul>
							  	</div>
							  	<div class="box-content">
							  		<table class="table table-normal">
							  			<thead>
											<tr>
												<td style="width: 40px">
													<div>
														<input type="checkbox" id="selectAll"
															title="<spring:message code="label.selectAll" />">
													</div>
												</td>
												<td><spring:message code="label.name" /></td>
												<td><spring:message code="label.email" /></td>
												<td><spring:message code="label.clientType" /></td>
												<td><spring:message code="label.birthday" /></td>
												<td><spring:message code="label.clientSince" /></td>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="client" items="${mlForm.allClients}" varStatus="status">
												<tr>
													<td>
														<div>
															<form:checkbox path="allClients[${status.index}].checked" aux="clientsCB"/>
															<form:hidden path="allClients[${status.index}].id"/>
														</div>
													</td>
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
  						</li>
					</ul>
				</div>
			</form:form>
		</div>
		<!-- row -->
	</div>

</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#selectAll").click(function() {
			$('input[aux=clientsCB]').prop('checked', $(this).is(':checked'));
		}); 
	});
</script>

