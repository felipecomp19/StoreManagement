<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-6">
		<form:form class="fill-up" method="POST" modelAttribute="mailingList" commandname="mailingList" action="${pageContext.request.contextPath}/mailingList/save">
			<div class="row">
				<div class="col-lg-6">
					<ul class="padded separate-sections">
						<li>
							<h4><spring:message code="label.createYourNewList" /></h4>
						</li>
						<li class="input">
							<input type="hidden" name="id" value="${mailingList.id}"/>
							<input type="hidden" name="createdOn" value="${mailingList.createdOn}"/>
							<input type="text" name="name" placeholder="<spring:message code="label.listName"/>" value="${mailingList.name}"/>
						</li>
						<li class="input">
							<input type="text" name="defaultFromName" placeholder="<spring:message code="label.defaultFromName"/>" value="${mailingList.defaultFromName}"/>
						</li>
						<li class="input">
							<input type="text" name="defaultFromEmail" placeholder="<spring:message code="label.defaultFromEmail"/>" value="${mailingList.defaultFromEmail}"/>
						</li>
						<li class="input">
							<input type="text" name="defaultSubject" placeholder="<spring:message code="label.defaultSubject"/>" value="${mailingList.defaultSubject}"/>
						</li>
					</ul>
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-blue"><spring:message code="label.save" /></button>
				<a href="${pageContext.request.contextPath}/mailingList/list">
					<button type="button" class="btn btn-default"><spring:message code="label.cancel" /></button>
				</a>
			</div>
		</form:form>
	</div>
</div>