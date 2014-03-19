<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-md-12">
		<form:form class="fill-up validatable" method="POST" commandName="indicator" modelAttribute="indicator" action="${pageContext.request.contextPath}/indicator/save" >
			<div class="row">
				<div class="col-md-6">
					
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-blue"><spring:message code="label.save" /></button>
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function () { 
		// Configuração para campos de Real.
		$(".money").maskMoney();
		$(".money").maskMoney().maskMoney('mask');
	});
</script>
