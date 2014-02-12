<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-6">
		<form:form class="fill-up" method="POST" modelAttribute="client" commandname="client" action="${pageContext.request.contextPath}/client/save" >
			<div class="row">
				<div class="col-lg-6">
					<ul class="padded separate-sections">
						<li><h4><spring:message code="label.stores"/></h4></li>
						<li>
							<div id="storesCB">
								<form:checkboxes id="stores" items="${stores}" path="stores" itemValue="idAsString" itemLabel="name" class="icheck"/>
							</div>
						</li>
						<li class="input">
							<label><spring:message code="label.cpf"/></label>
							<input type="text" id="cpf" name="cpf" placeholder="<spring:message code="label.cpf"/>" value="${client.cpf}" class="validate[required]" data-prompt-position="right"/>
							<span class="help-block note"><i class="icon-warning-sign"></i><spring:message code="warning.typeJustNumbers"/></span>
						</li>
						<li class="input">
							<input type="hidden" name="id" value="${client.id}"/>
							<input type="hidden" name="createdOn" value="${client.createdOn}"/>
							<label><spring:message code="label.name"/></label>
							<input type="text" name="name" placeholder="<spring:message code="label.name"/>" value="${client.name}" class="validate[required]" data-prompt-position="right"/>
						</li>
						<li class="input">
							<label><spring:message code="label.email"/></label>
							<input type="text" name="email" placeholder="<spring:message code="label.email"/>" value="${client.email}" class="validate[required|email]" data-prompt-position="right"/>
						</li>
						<li class="input">
							<input type="text" id="birthday" name="birthday" class="datepicker fill-up" placeholder="<spring:message code="label.birthday"/>" value="${client.birthday}"/>
						</li>
						<li class="input">
							<label><spring:message code="label.selectClientType" /></label>
							<form:select path="clientType" items="${clientTypes}" itemValue="id" itemLabel="nameWithDescription" class="uniform"/>
						</li>
					</ul>
				</div>
				<div class="col-lg-6">
					<ul class="padded separate-sections">
						<li><h4><spring:message code="label.address" /></h4></li>
						<li class="input">
							<label><spring:message code="label.cep"/></label>
							<input type="text" name="address.cep" placeholder="<spring:message code="label.cep"/>" value="${client.address.cep}"/>
						</li>
						<li class="input">
							<label><spring:message code="label.street"/></label>
							<input type="text" name="address.street" placeholder="<spring:message code="label.street"/>" value="${client.address.street}"/>
						</li>
						<li class="input">
							<label><spring:message code="label.number"/></label>
							<input type="text" name="address.number" placeholder="<spring:message code="label.number"/>" value="${client.address.number}"/>
						</li>
						<li class="input">
							<label><spring:message code="label.city"/></label>
							<input type="text" name="address.city" placeholder="<spring:message code="label.city"/>" value="${client.address.city}"/>
						</li>
						<li class="input">
							<label><spring:message code="label.state"/></label>
							<input type="text" name="address.state" placeholder="<spring:message code="label.state"/>" value="${client.address.state}"/>
						</li>
						<li class="input">
							<label><spring:message code="label.neighbourhood"/></label>
							<input type="text" name="address.neighbourhood" placeholder="<spring:message code="label.neighbourhood"/>" value="${client.address.neighbourhood}"/>
						</li>
						<li class="input">
							<label><spring:message code="label.complement"/></label>
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

<script type="text/javascript">
    $(document).ready(function () {
    	$("#storesCB").find("span").css("padding-right","25px");
    	$("#cpf").mask("999.999.999-99");
    	
    	$("#birthday").change(function(){
    		alert($(this).val());
    		$(this).attr("value",$(this).val());
    	});
    	
    	$("#cpf").focusout(function(){
	   		var cpf = this.value;
    		
    		$.ajax({
	   			url: "${pageContext.request.contextPath}/client/getClientByCPF/" + cpf,
	   			type:"GET",
	   			dataType: "json",
	   			contentType: 'application/json',
	   		    mimeType: 'application/json',
	   		 	success: function(data) { 
	   	        	//alert(data.id + ":" + data.name);
	   	    	},
	   	    	error:function(data,status,er) { 
	   	        	//alert("error: "+data+" status: "+status+" er:"+er);
	   	     	}
	   		});
    	});
 	});
 </script>
