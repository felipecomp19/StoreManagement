<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form:form class="fill-up" method="POST" modelAttribute="mailingList" commandname="mailingList"
	action="${pageContext.request.contextPath}/mailingList/save">
	<div class="row">
		<div class="col-md-4">
			<ul class="padded separate-sections">
				<li>
					<h4>
						<spring:message code="label.createYourNewList" />
					</h4>
				</li>
				<li class="input">
					<input type="hidden" name="id" value="${mailingList.id}" /> 
					<input type="hidden" name="createdOn" value="${mailingList.createdOn}" /> 
					<input type="text" name="name" placeholder="<spring:message code="label.listName"/>" value="${mailingList.name}" />
				</li>
				<li class="input">
					<input type="text" name="defaultFromName" placeholder="<spring:message code="label.defaultFromName"/>" value="${mailingList.defaultFromName}" />
				</li>
				<li class="input"><input type="text" name="defaultFromEmail"
					placeholder="<spring:message code="label.defaultFromEmail"/>"
					value="${mailingList.defaultFromEmail}" /></li>
				<li class="input"><input type="text" name="defaultSubject"
					placeholder="<spring:message code="label.defaultSubject"/>"
					value="${mailingList.defaultSubject}" /></li>
			</ul>
		</div>
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
									<input type="checkbox" class="icheck" id="selectAll" title="<spring:message code="label.selectAll" />">
									<!-- <label for="selectAll" title=""><spring:message code="label.selectAll" /></label> -->
								</div>
							</th>
							<th><spring:message code="label.name" /></th>
							<th><spring:message code="label.email" /></th>
							<th><spring:message code="label.clientType" /></th>
							<th><spring:message code="label.clientSince" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="client" items="${mailingList.clients}">
							<tr>
								<td>
									<div>
										<input name="clientCB" type="checkbox" class="icheck" clientId="${client.id}" />
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
	</div>
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
<script type="text/javascript">
    $(document).ready(function () {
    	$("#selectAll").click(function(){
    		$('input[type=checkbox]').prop('checked', $(this).checked);
    	});
    	
    	$('input[name=clientCB]').click(function(){
    		var clientId = $(this).prop("clientId");
    		if($(this).is(':checked'))
    			alert("checked: " + clientId);
    		else
    			alert("not checked");
    	});
 	});
</script>

