<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form:form class="fill-up" method="POST" modelAttribute="campaing" action="${pageContext.request.contextPath}/campaign/save">
	<div class="row">
		<div class="col-md-6">
			<ul class="padded separate-sections">
				<li>
					<label><spring:message code="label.fillTheCamapaingInformations" /></label>
					<input type="hidden" name="id" value="${campaign.id}" /> 
					<input type="hidden" name="createdOn" value="${campaign.createdOn}" />
					<input type="text" name="name" placeholder="<spring:message code="label.campaignName"/>" value="${campaign.name}" />
				</li>
				<li>
					<textarea name="description" rows="3" placeholder="<spring:message code="label.campaignDescription" />"></textarea>
				</li>
			</ul>
		</div>
		<div class="col-md-6">
			<ul class="padded separate-sections">
				<li>
					<label><spring:message code="label.selectMailingList" /> </label>
					<form:select path="mailingList" items="${mailingLists}" itemValue="id" itemLabel="name" class="uniform"/>
				</li>
			</ul>
		</div>
	</div>	
	<div class="row">
		<div class="col-md-12">
			<ul class="padded separate-sections">
				<li>
					<h4>Email</h4>
				</li>
				<li>
					<textarea id="emailContent" name="emailContent"></textarea>
				</li>
			</ul>
		</div>
	</div>
	<div class="form-actions">
		<button type="submit" class="btn btn-blue"><spring:message code="label.save" /></button>
		<a href="${pageContext.request.contextPath}/campaign/list">
			<button type="button" class="btn btn-default"><spring:message code="label.cancel" /></button>
		</a>
	</div>
</form:form>
<script type="text/javascript">
    $(document).ready(function () {
    	$("#emailContent").cleditor();
    	
 	});
    
    /* function showValue()
    {
    	var v = $("#email").val();
    	
    	alert(v);
    	
    	return;
    } */
 </script>