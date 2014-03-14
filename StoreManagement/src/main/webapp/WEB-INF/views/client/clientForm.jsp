<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
	.break-word{
		word-wrap: break-word;
	}
</style>
<div class="row">
	<div class="col-md-12">
		<form:form class="fill-up validatable" method="POST" modelAttribute="client" commandname="client" action="${pageContext.request.contextPath}/client/save" >
			<div class="row">
				<div class="col-md-3">
					<ul class="padded separate-sections">
						<li><h4><spring:message code="label.stores"/></h4></li>
						<li>
							<div id="storesCB">
								<ul  class="padded separate-sections">
									<form:checkboxes id="stores" items="${stores}" path="stores" itemValue="idAsString" itemLabel="nameWithDesc" class="icheck break-word validate[required]" element="li" />
								</ul>
							</div>
						</li>
						
					</ul>
				</div>
				<div class="col-md-4">
					<ul class="padded separate-sections">
					<li><h4><spring:message code="label.clientData" /></h4></li>
						<li class="input" style="width: 200px;">
							<label><spring:message code="label.cpf"/></label>
							<input type="text" id="cpf" name="cpf" placeholder="<spring:message code="label.cpf"/>" value="${client.cpf}" class="validate[required]" data-prompt-position="topLeft"/>
							<span class="help-block note"><i class="icon-warning-sign"></i><spring:message code="warning.typeJustNumbers"/></span>
						</li>
						<li class="input">
							<input id="id" type="hidden" name="id" value="${client.id}"/>
							<input id="createdOn" type="hidden" name="createdOn" value="${client.createdOn}"/>
							<label><spring:message code="label.name"/></label>
							<input id="name" type="text" name="name" placeholder="<spring:message code="label.name"/>" value="${client.name}" class="validate[required]" data-prompt-position="topLeft"/>
						</li>
						<li class="input">
							<label><spring:message code="label.email"/></label>
							<input id="email" type="text" name="email" placeholder="<spring:message code="label.email"/>" value="${client.email}" class="validate[required,email]" data-prompt-position="topLeft"/>
						</li>
						<li class="input">
							<div class="row action-nav-row">
								<div class="col-md-6">
									<label><spring:message code="label.telephone"/></label>
									<input type="text" id="telephone" name="telephone" placeholder="<spring:message code="label.telephone"/>" value="${client.telephone}" />
								</div>
								<div class="col-md-6">
									<label><spring:message code="label.cellPhone"/></label>
									<input type="text" id="cellPhone" name="cellPhone" placeholder="<spring:message code="label.cellPhone"/>" value="${client.cellPhone}" />
								</div>
							</div>
						</li>
						<li class="input">
							<label class="control-label"><spring:message code="label.birthday" /></label>
							<div class="row action-nav-row">
								<div class="col-md-1" align="right">
									<label class="control-label"><spring:message code="label.day" /></label>
								</div>
								<div class="col-md-3">
									<form:select id="day_birthday" path="day_birthday" items="${daysSL}" class="uniform" />
								</div>
								<div class="col-md-1" align="right" style="vertical-align: bottom;">
									<label class="control-label"><spring:message code="label.month" /></label>
								</div>
								<div class="col-md-3">
									<form:select id="month_birthday" path="month_birthday" class="uniform">
										<form:option value="1"><spring:message code="month.jan"/></form:option>
										<form:option value="2"><spring:message code="month.feb"/></form:option>
										<form:option value="3"><spring:message code="month.mar"/></form:option>
										<form:option value="4"><spring:message code="month.apr"/></form:option>
										<form:option value="5"><spring:message code="month.may"/></form:option>
										<form:option value="6"><spring:message code="month.jun"/></form:option>
										<form:option value="7"><spring:message code="month.jul"/></form:option>
										<form:option value="8"><spring:message code="month.ago"/></form:option>
										<form:option value="9"><spring:message code="month.set"/></form:option>
										<form:option value="10"><spring:message code="month.oct"/></form:option>
										<form:option value="11"><spring:message code="month.nov"/></form:option>
										<form:option value="12"><spring:message code="month.dez"/></form:option>
									</form:select> 
								</div>
							</div>
						</li>
						<li class="input">
							<div class="row action-nav-row">
								<div class="col-md-6">
									<label><spring:message code="label.selectClientType" /></label>
									<form:select id="clientType" path="clientType" items="${clientTypes}" itemValue="id" itemLabel="nameWithDescription" class="uniform"/>
								</div>
								<div class="col-md-6">
									<label><spring:message code="label.especialActions" /></label>
									<input type="text" name="especialActions" placeholder="<spring:message code="label.especialActions" />" value="${client.especialActions}" />
								</div>
							</div>
						</li>
					</ul>
				</div>
				<div class="col-md-4">
					<ul class="padded separate-sections">
						<li><h4><spring:message code="label.address" /></h4></li>
						<li class="input" style="width: 200px;">
							<label><spring:message code="label.cep"/></label>
							<input type="text" id="cep" name="address.cep" placeholder="<spring:message code="label.cep"/>" value="${client.address.cep}"/>
							<span class="help-block note"><i class="icon-warning-sign"></i><spring:message code="warning.typeJustNumbers"/></span>
						</li>
						<li class="input">
							<div class="row action-nav-row">
								<div class="col-md-8">
									<label><spring:message code="label.street"/></label>
									<input type="text" id="street" name="address.street" placeholder="<spring:message code="label.street"/>" value="${client.address.street}"/>
								</div>
								<div class="col-md-4">
									<label><spring:message code="label.number"/></label>
									<input id="number" type="text" name="address.number" placeholder="<spring:message code="label.number"/>" value="${client.address.number}"/>
								</div>
							</div>
						</li>
						<li class="input">
							<div class="row action-nav-row">
								<div class="col-md-8">
									<label><spring:message code="label.city"/></label>
									<input id="city" type="text" name="address.city" placeholder="<spring:message code="label.city"/>" value="${client.address.city}"/>
								</div>
								<div class="col-md-4">
									<label><spring:message code="label.state"/></label>
							<input id="statee" type="text"  name="address.state" placeholder="<spring:message code="label.state"/>" value="${client.address.state}"/>
								</div>
							</div>
						</li>
						<li class="input">
							<label><spring:message code="label.neighbourhood"/></label>
							<input type="text" id="neighbourhood" name="address.neighbourhood" placeholder="<spring:message code="label.neighbourhood"/>" value="${client.address.neighbourhood}"/>
						</li>
						<li class="input">
							<label><spring:message code="label.complement"/></label>
							<input id="complement" type="text" name="address.complement" placeholder="<spring:message code="label.complement"/>" value="${client.address.complement}"/>
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
    	$("#telephone").mask("(99) 9999-99999");
    	$("#cellPhone").mask("(99) 9999-99999");
    	$("#cep").mask("99.999-999");

    	$("#cpf").focusout(function(){
	   		var cpf = this.value.replace(/[^\d]/g, "");
    		
	   		if(cpf != ''){
	   			$.blockUI({
	   	            message: '<h3> Consultando <img src="${pageContext.request.contextPath}/resources/coreAdmin/images/loading.gif" /></h3>'
	   	        });
		   		$.ajax({
		   			url: "${pageContext.request.contextPath}/client/getClientByCPF/" + cpf,
		   			type:"GET",
		   			dataType: "json",
		   			contentType: 'application/json',
		   		    mimeType: 'application/json', 
		   		 	success: function(client) { 
		   		 		alert(client.id);
		   		 		alert(client.id > 0);
		   	        	if(client.id > 0){
		   	        		window.location.replace("${pageContext.request.contextPath}/client/edit/" + client.id);
			   	        	return Growl.info({
		                        title:'Cliente já cadastrado!',
		                        text: 'Atualizando informações'
			   	        	});
		   	        	}else{
		   	        		$("#id").val('');
			   	        	$("#createdOn").val('');
			   	        	$("#name").val('');
			   	        	$("#email").val('');
			   	        	$("#telephone").val('');
			   	        	
			   	        	$("#cep").val('');
			   	        	$("#street").val('');
			   	        	$("#number").val('');
			   	        	$("#city").val('');
			   	        	$("#statee").val('');
			   	        	$("#neighbourhood").val('');
			   	        	$("#complement").val('');
			   	        	
			   	        	return Growl.info({
		                        title:'Novo cliente',
		                        text: 'Preencha o formulário'
			   	        	});
		   	        	}
		   	    	},
		   	    	error:function(data,status,er) { 
		   	    		return Growl.error({
	                        title:'Erro!',
	                        text: 'Erro ao consultar o CPF'
	                    });
		   	     	},
	                complete: function() {
	                    $.unblockUI();
	                }
		   		});
	   		}
    	});
    	
    	
    	$("#cep").focusout(function(){
	   		var cep = $(this).val().replace(/[^\d]/g, "");
    		
	   		if(cep != ''){
	   			$.blockUI({
	   	            message: '<h3> Consultando <img src="${pageContext.request.contextPath}/resources/coreAdmin/images/loading.gif" /></h3>'
	   	        }); 
	    		$.ajax({
	                url: "http://cep.correiocontrol.com.br/" + cep + ".json",
	                type: "GET",
	                dataType: "json",
	                success: function(json) {
	                	$("#street").val(json.logradouro);
	                    $("#neighbourhood").val(json.bairro);
	                    $("#city").val(json.localidade);
	                    $("#statee").val(json.uf);
	                },
	                error: function() {
	                	return Growl.error({
	                        title:'Erro!',
	                        text: 'CEP não encontrado'
	                    });
	                    $('#street').val('');
	                	$('#neighbourhood').val('');
	                    $('#city').val('');
	                    $('#statee').val('');
	                },
	                complete: function() {
	                    $.unblockUI();
	                }
	            });
	   		}
	   		event.preventDefault();
    	});
 	});
 </script>
