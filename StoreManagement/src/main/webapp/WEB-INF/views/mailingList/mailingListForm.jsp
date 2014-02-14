<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-12">
		<div class="row">
			<div class="col-md-4">
				<form:form class="fill-up" method="POST"
					modelAttribute="mailingList" commandname="mailingList"
					action="${pageContext.request.contextPath}/mailingList/save">
					<ul class="padded separate-sections">
						<li>
							<h4>
								<spring:message code="label.createYourNewList" />
							</h4>
						</li>
						<li class="input"><input type="hidden" name="id"
							value="${mailingList.id}" /> <input type="hidden"
							name="createdOn" value="${mailingList.createdOn}" /> <label><spring:message
									code="label.listName" /></label> <input type="text" name="name"
							placeholder="<spring:message code="label.listName"/>"
							value="${mailingList.name}" /></li>
						<li class="input"><label><spring:message
									code="label.defaultFromName" /></label> <input type="text"
							name="defaultFromName"
							placeholder="<spring:message code="label.defaultFromName"/>"
							value="${mailingList.defaultFromName}" /></li>
						<li class="input"><label><spring:message
									code="label.defaultFromEmail" /></label> <input type="text"
							name="defaultFromEmail"
							placeholder="<spring:message code="label.defaultFromEmail"/>"
							value="${mailingList.defaultFromEmail}" /></li>
						<li class="input"><label><spring:message
									code="label.defaultSubject" /></label> <input type="text"
							name="defaultSubject"
							placeholder="<spring:message code="label.defaultSubject"/>"
							value="${mailingList.defaultSubject}" /></li>
					</ul>
					<input type="hidden" id="clients" name="clientsList"/>
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
				</form:form>
			</div>


			<!-- List of clients -->
			<div class="col-md-8">
				<ul class="padded separate-sections">
					<li>
						<h4>
							<spring:message code="label.selectClients" />
						</h4>
					</li>
				</ul>
				<div id="dataTables">
					<table class="dTable responsive">
						<thead>
							<tr>
								<th style="width: 40px">
									<div>
										<input type="checkbox" id="selectAll"
											title="<spring:message code="label.selectAll" />">
									</div>
								</th>
								<th><spring:message code="label.name" /></th>
								<th><spring:message code="label.email" /></th>
								<th><spring:message code="label.clientType" /></th>
								<th><spring:message code="label.clientSince" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="client" items="${clients}">
								<tr>
									<td>
										<div>
											<input type="checkbox" name="clientCB" clientId="${client.id}" />
										</div>
									</td>
									<td>${client.name}</td>
									<td>${client.email}</td>
									<td>${client.clientType.name}</td>
									<td>${client.createdOn}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!-- [END] List of clients -->
		</div>
		<!-- row -->
	</div>

</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#selectAll").click(function() {
			
			$('input[name=clientCB]').prop('checked', $(this).is(':checked'));
		}); 

		$("input[name=clientCB]").click(function() {
			var clientId = $(this).attr("clientId");
			
			var currentClients = $("#clients").attr("value");
			if ($(this).is(':checked')){
				$("#clients").attr("value", clientId);
				alert("checked: " + $("#clients").attr("value"));
			}
			
			else
				alert("not checked");
		});
	});
</script>

