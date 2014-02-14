<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-12">
		<form:form class="fill-up validatable" method="POST" modelAttribute="client" commandname="client" action="${pageContext.request.contextPath}/client/save" >
			<div class="row">
				<div class="col-md-2">
					<ul class="padded separate-sections">
						<li><h4><spring:message code="label.stores"/></h4></li>
						<li>
							<div id="storesCB">
								<%-- <c:forEach var="store" items="${stores}">
									<div>
										<form:checkbox path="stores" value="${store.idAsString}" label="${store.nameWithDesc}" class="icheck"/>
									</div>
								</c:forEach> --%>
								<form:checkboxes id="stores" items="${stores}" path="stores" itemValue="idAsString" itemLabel="nameWithDesc" class="icheck"/>
							</div>
						</li>
						
					</ul>
				</div>
				<div class="col-md-4">
					<ul class="padded separate-sections">
					<li><h4><spring:message code="label.clientData" /></h4></li>
						<li class="input">
							<label><spring:message code="label.cpf"/></label>
							<input type="text" id="cpf" name="cpf" placeholder="<spring:message code="label.cpf"/>" value="${client.cpf}" class="validate[required]" data-prompt-position="topLeft"/>
							<span class="help-block note"><i class="icon-warning-sign"></i><spring:message code="warning.typeJustNumbers"/></span>
						</li>
						<li class="input">
							<input type="hidden" name="id" value="${client.id}"/>
							<input type="hidden" name="createdOn" value="${client.createdOn}"/>
							<label><spring:message code="label.name"/></label>
							<input type="text" name="name" placeholder="<spring:message code="label.name"/>" value="${client.name}" class="validate[required]" data-prompt-position="topLeft"/>
						</li>
						<li class="input">
							<label><spring:message code="label.email"/></label>
							<input type="text" name="email" placeholder="<spring:message code="label.email"/>" value="${client.email}" class="validate[required|email]" data-prompt-position="topLeft"/>
						</li>
						<li class="input">
							<label><spring:message code="label.telephone"/></label>
							<input type="text" id="telephone" name="telephone" placeholder="<spring:message code="label.telephone"/>" value="${client.telephone}" />
						</li>
						<li class="input">
							<label class="control-label"><spring:message code="label.day" /></label>
							<form:select path="day_birthday" items="${daysSL}" class="uniform" />
							<label class="control-label"><spring:message code="label.month" /></label>
							<form:select path="month_birthday" items="${monthSL}" class="uniform" />
						</li>
						<li class="input">
							<label><spring:message code="label.selectClientType" /></label>
							<form:select path="clientType" items="${clientTypes}" itemValue="id" itemLabel="nameWithDescription" class="uniform"/>
						</li>
					</ul>
				</div>
				<div class="col-md-4">
					<ul class="padded separate-sections">
						<li><h4><spring:message code="label.address" /></h4></li>
						<li class="input">
							<label><spring:message code="label.cep"/></label>
							<input type="text" id="cep" name="address.cep" placeholder="<spring:message code="label.cep"/>" value="${client.address.cep}"/>
							<span class="help-block note"><i class="icon-warning-sign"></i><spring:message code="warning.typeJustNumbers"/></span>
						</li>
						<li class="input">
							<label><spring:message code="label.street"/></label>
							<input type="text" id="street" name="address.street" placeholder="<spring:message code="label.street"/>" value="${client.address.street}"/>
						</li>
						<li class="input">
							<label><spring:message code="label.number"/></label>
							<input type="text" name="address.number" placeholder="<spring:message code="label.number"/>" value="${client.address.number}"/>
						</li>
						<li class="input">
							<label><spring:message code="label.city"/></label>
							<input type="text" id="city" name="address.city" placeholder="<spring:message code="label.city"/>" value="${client.address.city}"/>
						</li>
						<li class="input">
							<label><spring:message code="label.state"/></label>
							<input type="text" id="statee" name="address.state" placeholder="<spring:message code="label.state"/>" value="${client.address.state}"/>
						</li> 
						<li class="input">
							<label><spring:message code="label.neighbourhood"/></label>
							<input type="text" id="neighbourhood" name="address.neighbourhood" placeholder="<spring:message code="label.neighbourhood"/>" value="${client.address.neighbourhood}"/>
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
    	$("#telephone").mask("(99) 9999-99999");
    	$("#cep").mask("99.999-999");
    	
    	/* $("#telephone").mask("(99) 9999-9999?9").ready(function(event) {
            var target, phone, element;
            target = (event.currentTarget) ? event.currentTarget : event.srcElement;
            phone = target.value.replace(/\D/g, '');
            element = $(target);
            element.unmask();
            if(phone.length > 10) {
                element.mask("(99) 99999-999?9");
            } else {
                element.mask("(99) 9999-9999?9");
            }
        }); */
    	
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
		   		 	success: function(data) { 
		   	        	alert(data.id + ":" + data.name);
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
    		event.preventDefault();
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
