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
							<%-- <input type="hidden" name="mailingList.createdOn" value="${mlForm.mailingList.createdOn}" /> --%>
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
						<a href="${pageContext.request.contextPath}/mailingList/list" class="btn btn-default">
							<spring:message code="label.cancel" />
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
							      		<li>
							      			<div class="btn-group">
                								<%-- <button class="btn btn-green btn-xs"><spring:message code="label.filters" /></button>
              									<button class="btn btn-green btn-xs dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button> --%>	
                 								<button class="btn btn-green btn-xs dropdown-toggle" data-toggle="dropdown"><spring:message code="label.filters" />
                 									<span class="caret"></span>
               									</button>
							                    <ul class="dropdown-menu">
							                    	<li><a data-toggle="modal" href="#modal-birthday-form"><spring:message code="label.birthday" /></a></li>
							                        <li><a data-toggle="modal" href="#modal-clientType-form"><spring:message code="label.clientType" /></a></li>
							                        <li><a data-toggle="modal" href="#modal-store-form"><spring:message code="label.store" /></a></li>
							                        <li class="divider"></li>
							                        <c:choose>
							                        	<c:when test="${mlForm.mailingList.id > 0}">
							                        		<li><a href="${pageContext.request.contextPath}/mailingList/edit/${mlForm.mailingList.id}"><spring:message code="label.cleanFilters" /></a></li>
							                        	</c:when>
							                        	<c:otherwise>
							                        		<li><a href="${pageContext.request.contextPath}/mailingList/create"><spring:message code="label.cleanFilters" /></a></li>
							                        	</c:otherwise>
							                        </c:choose>
							                        
							                    </ul>
						                    </div>
						      			</li>
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
	
	<!-- MODAL FORMS -->
	<div id="modal-birthday-form" class="modal fade">
  		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        			<h6 id="modal-formLabel"><spring:message code="label.birthday" /></h6>
      			</div>
      			<div class="modal-body">
        			<form:form commandName="mailingList" modelAttribute="mailingList" class="form-horizontal fill-up separate-sections" action="${pageContext.request.contextPath}/mailingList/filterByBirthdayMonth" method="POST">
        				<input type="hidden" name="id" value="${mlForm.mailingList.id}" /> 
						<input type="hidden" name="name" value="${mlForm.mailingList.name}" />
						<input type="hidden" name="defaultFromName" value="${mlForm.mailingList.defaultFromName}" />
						<input type="hidden" name="defaultFromEmail" value="${mlForm.mailingList.defaultFromEmail}" />
						<input type="hidden" name="defaultSubject" value="${mlForm.mailingList.defaultSubject}" />
        				<div class="row action-nav-row">
            				<div class="col-md-6">
              					<label><spring:message code="label.selectMonth"/></label>
              					<select id="monthSL" class="chzn-select" name="monthSL">
									<option value="1"><spring:message code="month.jan"/></option>
									<option value="2"><spring:message code="month.feb"/></option>
									<option value="3"><spring:message code="month.mar"/></option>
									<option value="4"><spring:message code="month.apr"/></option>
									<option value="5"><spring:message code="month.may"/></option>
									<option value="6"><spring:message code="month.jun"/></option>
									<option value="7"><spring:message code="month.jul"/></option>
									<option value="8"><spring:message code="month.ago"/></option>
									<option value="9"><spring:message code="month.set"/></option>
									<option value="10"><spring:message code="month.oct"/></option>
									<option value="11"><spring:message code="month.nov"/></option>
									<option value="12"><spring:message code="month.dez"/></option>
								</select>
							</div>
							<div class="col-md-3">
								<label><spring:message code="label.dayFrom" /></label>
								<select id="dayFromSL" name="dayFromSL" class="uniform">
									<c:forEach items="${days}" var="day">
										<option value="${day}">${day}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-3">
								<label><spring:message code="label.dayTo" /></label>
								<select id="dayToSL" name="dayToSL" class="uniform">
									<c:forEach items="${days}" var="day">
										<option value="${day}">${day}</option>
									</c:forEach>
								</select>
							</div>
      					</div>
      					<div class="modal-footer">
        					<button class="btn btn-default" data-dismiss="modal"><spring:message code="label.cancel" /></button>
        					<button class="btn btn-blue"><spring:message code="label.filter" /></button>
      					</div>
        			</form:form>
        		</div>
        	</div>
        </div>
    </div>
    
    <div id="modal-clientType-form" class="modal fade">
  		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        			<h6 id="modal-formLabel"><spring:message code="label.clientTypes" /></h6>
      			</div>
      			<div class="modal-body">
        			<form:form commandName="mailingList" modelAttribute="mailingList" class="form-horizontal fill-up separate-sections" action="${pageContext.request.contextPath}/mailingList/filterByClientType" method="POST">
        				<input type="hidden" name="id" value="${mlForm.mailingList.id}" /> 
						<input type="hidden" name="name" value="${mlForm.mailingList.name}" />
						<input type="hidden" name="defaultFromName" value="${mlForm.mailingList.defaultFromName}" />
						<input type="hidden" name="defaultFromEmail" value="${mlForm.mailingList.defaultFromEmail}" />
						<input type="hidden" name="defaultSubject" value="${mlForm.mailingList.defaultSubject}" />
        				<div class="row">
            				<div class="col-md-12">
              					<label><spring:message code="label.selectDisiredClientType"/></label>
              					<select id="cliTypeSL" class="chzn-select" name="cliTypeSL">
									<c:forEach items="${clientTypes}" var="cliType">
										<option value="${cliType.id}">${cliType.nameWithDescription}</option>
									</c:forEach>
								</select>
           					</div>
      					</div>
      					<div class="modal-footer">
        					<button class="btn btn-default" data-dismiss="modal"><spring:message code="label.cancel" /></button>
        					<button class="btn btn-blue" type="submit"><spring:message code="label.filter" /></button>
      					</div>
        			</form:form>
        		</div>
        	</div>
        </div>
    </div>
    
    <div id="modal-store-form" class="modal fade">
  		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        			<h6 id="modal-formLabel"><spring:message code="label.stores" /></h6>
      			</div>
      			<div class="modal-body">
        			<form:form commandName="mailingList" modelAttribute="mailingList" class="form-horizontal fill-up separate-sections" action="${pageContext.request.contextPath}/mailingList/filterByStore" method="POST">
        				<input type="hidden" name="id" value="${mlForm.mailingList.id}" /> 
						<input type="hidden" name="name" value="${mlForm.mailingList.name}" />
						<input type="hidden" name="defaultFromName" value="${mlForm.mailingList.defaultFromName}" />
						<input type="hidden" name="defaultFromEmail" value="${mlForm.mailingList.defaultFromEmail}" />
						<input type="hidden" name="defaultSubject" value="${mlForm.mailingList.defaultSubject}" />
        				<div class="row">
            				<div class="col-md-12">
              					<label><spring:message code="label.selectDisiredStore"/></label>
              					<select id="storeSL" class="chzn-select" name="storeSL">
									<c:forEach items="${stores}" var="store">
										<option value="${store.id}">${store.nameWithDesc}</option>
									</c:forEach>
								</select>
           					</div>
      					</div>
      					<div class="modal-footer">
        					<button class="btn btn-default" data-dismiss="modal"><spring:message code="label.cancel" /></button>
        					<button class="btn btn-blue" type="submit"><spring:message code="label.filter" /></button>
      					</div>
        			</form:form>
        		</div>
        	</div>
        </div>
    </div>
	<!-- [END] MODAL FORMS -->

</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#selectAll").click(function() {
			$('input[aux=clientsCB]').prop('checked', $(this).is(':checked'));
		}); 
	});
</script>

