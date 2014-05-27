<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-12">
		<div class="pull-left wizard-steps col-md-4" style="height: 425px;">
			<div class="wizard-nav-container" style="height: 360px;">
				<ul class="nav nav-list" style="padding-bottom: 30px;">
					<li class="wizard-nav-item active">
						<a id="step1" class="wizard-nav-link steps"><i class="icon-chevron-right"></i>Nome</a>
					</li>
					<li class="wizard-nav-item">
						<a id="step2" class="wizard-nav-link steps"><i class="icon-chevron-right"></i>Clientes</a>
					</li>
					<li class="wizard-nav-item">
						<a id="step3" class="wizard-nav-link steps"><i class="icon-chevron-right"></i>Imagem</a>
					</li>
					<li class="wizard-nav-item">
						<a id="step4" class="wizard-nav-link steps"><i class="icon-chevron-right"></i>Mensagem</a>
					</li>
					<li class="wizard-nav-item">
						<a id="step5" class="wizard-nav-link steps"><i class="icon-chevron-right"></i>Resultado</a>
					</li>
				</ul>
			</div>
	
			<div class="wizard-progress-container">
				<div class="progress">
					<div class="progress-bar" style="width: 0%;"></div>
				</div>
			</div>
		</div>
		
		<div class="wizard-cards col-md-8">
			<div class="wizard-card-container" style="min-height: 425px;">
				<div id="step1Content" style="height: 300px;">
					<div class="col-md-12">
						<form:form id="campaingForm" class="fill-up validatable" modelAttribute="campaign" action="#">
						<ul class="padded separate-sections">
							<li>
								<label><spring:message code="label.fillTheCamapaingInformations" /></label>
								<input type="hidden" name="id" value="${campaign.id}" /> 
								<input type="hidden" name="createdOn" value="${campaign.createdOn}" />
								<input type="hidden" name="emailFileName" value="${campaign.emailFileName}" />
								<input type="text" name="name" class="validate[required]" placeholder="<spring:message code="label.campaignName"/>" value="${campaign.name}" />
							</li>
							<li>
								<textarea name="description" class="validate[required]" rows="3" placeholder="<spring:message code="label.campaignDescription" />">${campaign.description}</textarea>
							</li>
						</ul>
						</form:form>
					</div>
				</div>
				<div id="step2Content" style="height: 300px;">
					<p>bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb</p>
				</div>
				<div id="step3Content" style="height: 300px;">
					<p>cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc</p>
				</div>
				<div id="step4Content" style="height: 300px;">
					<p>D</p>
				</div>
				<div id="step5Content" style="height: 300px;">
					<p>F</p>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="modal-clientTable" class="modal fade">
	<div class="modal-dialog">
    	<div class="modal-content">
      		<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        		<h6 id="modal-tablesLabel"><spring:message code="label.clients" /></h6>
      		</div>
    		<div class="modal-body nopadding">
       		<!-- find me in partials/data_tables_custom -->
				<div id="dataTables">
					<table id="clientsTable" cellpadding="0" cellspacing="0" border="0" class="dTable responsive">
			  			<thead>
							<tr>
								<td><spring:message code="label.name" /></td>
								<td><spring:message code="label.email" /></td>
								<td><spring:message code="label.clientType" /></td>
								<td><spring:message code="label.birthday" /></td>
								<td><spring:message code="label.clientSince" /></td>
							</tr>
						</thead>
						<tbody>
						</tbody>
		  			</table>
				</div>
			</div>
			<div class="modal-footer">
        		<button class="btn btn-default" data-dismiss="modal"><spring:message code="label.cancel"/></button>
      		</div>
		</div>
	</div>
</div>

<div id="modal-warnSendEmails" class="modal fade">
	<div class="modal-dialog">
    	<div class="modal-content">
    		<form:form method="POST" modelAttribute="campaign" action="${pageContext.request.contextPath}/campaign/sendEmails">
    			<input type="hidden" name="id" value="${campaign.id}">
    			<input type="hidden" name="mailingList.id" value="${campaign.mailingList.id}" id="selectedML">
	      		<div class="modal-header">
	        		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        		<h6 id="modal-tablesLabel"><spring:message code="label.sendEmails" /></h6>
	      		</div>
	    		<div class="modal-body padding">
	   				<span id="numberOfClients"></span>
	   				<label>
	   					<p>
	   						<i class="icon-warning-sign"></i> Esta campanha será enviada para <span name="clientesNumber"></span> cliente(s). Deseja continuar?
   						</p>
   					</label>
	    		</div>
	    		<div class="modal-footer">
	        		<button class="btn btn-red" data-dismiss="modal"><spring:message code="label.cancel" /></button>
	        		<button class="btn btn-green" type="submit"><spring:message code="label.continue" /></button>
	      		</div>
      		</form:form>
   		</div>
  	</div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
    	
    	hideAllSteps();
    	$("#step1Content").show();
    	
    	$("#step1").click(function(){
    		changeWizardStep();
        	$(this).addClass("active");
    		$("#step1Content").show();
    	});
    	
    	$("#step2").click(function(){
    		changeWizardStep();
        	$(this).addClass("active");
    		$("#step2Content").show();
    	});
    	
    	$("#step3").click(function(){
    		changeWizardStep();
        	$(this).addClass("active");
    		$("#step3Content").show();
    	});
    	
    	$("#step4").click(function(){
    		changeWizardStep();    		
        	$(this).addClass("active");
    		$("#step4Content").show();
    	});
    	
    	$("#step5").click(function(){
    		changeWizardStep();		
        	$(this).addClass("active");
    		$("#step5Content").show();
    	});
    	
    	function hideAllSteps(){
    		$("#step1Content").hide();
    		$("#step2Content").hide();
        	$("#step3Content").hide();
        	$("#step4Content").hide();
        	$("#step5Content").hide();
    	};
    	
    	function removeAllActiveClass(){
    		$("#step1").removeClass("active");
    		$("#step2").removeClass("active");
    		$("#step3").removeClass("active");
    		$("#step4").removeClass("active");
    		$("#step5").removeClass("active");
    	};
    	
    	function changeWizardStep(){
    		hideAllSteps();
    		removeAllActiveClass();
    	};
    	
    	/* $(".steps").click(function (){
    		$(".stepContent").each(function(){
    			$(this).hide();
    		});
    		
    		var stepId = $(this).attr("id");
    		var contentId = "#" + stepId + "Content";
    		
			$(contentId).show();
    	}); */
 	});
 </script>