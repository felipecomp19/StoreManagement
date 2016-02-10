<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-12">
		<div class="pull-left wizard-steps col-md-2" style="height: 425px;">
			<div class="wizard-nav-container" style="height: 360px;">
				<ul class="nav nav-list" style="padding-bottom: 30px;">
					<li id="step1li" class="wizard-nav-item active">
						<a id="step1" class="wizard-nav-link steps"><i class="icon-chevron-right"></i>Dados da campanha</a>
					</li>
					<li id="step2li" class="wizard-nav-item">
						<a id="step2" class="wizard-nav-link steps"><i class="icon-chevron-right"></i>Mensagem</a>
					</li>
					<li id="step3li" class="wizard-nav-item">
						<a id="step3" class="wizard-nav-link steps"><i class="icon-chevron-right"></i>Resultado</a>
					</li>
				</ul>
			</div>
	
			<div class="wizard-progress-container">
				<div class="progress">
					<div id="campaing-progress-bar" class="progress-bar" style="width: 33%;"></div>
				</div>
			</div>
		</div>
		
		<!-- <div class="wizard-cards col-md-8"> -->
		<div class="col-md-8">
			<div class="wizard-card-container" style="min-height: 425px;">
				<div class="container padded">
					<div id="step1Content" style="min-height: 500px;">
						<form:form id="campaingForm" class="fill-up validatable" modelAttribute="campaign" action="${pageContext.request.contextPath}/campaign/save">
							<div class="row"> 
							<!-- <div class="col-md-12"> -->
								<ul class="padded separate-sections">
									<li>
										<label><spring:message code="label.fillTheCamapaingInformations" /></label>
										<input type="hidden" id="id" name="id" value="${campaign.id}" /> 
										<input type="hidden" id="createdOn" name="createdOn" value="${campaign.createdOn}" />
										<input type="hidden" id="emailFileName" name="emailFileName" value="${campaign.emailFileName}" />
										
										<input type="hidden" id="submitted" name="submitted" value="${campaign.submitted}" />
										<input type="hidden" id="submittedDate" name="submittedDate" value="${campaign.submittedDate}" />
										<input type="hidden" id="statusId" name="statusId" value="${campaign.status.id}" />
										
										<input type="text" id="name" name="name" class="validate[required]" placeholder="<spring:message code="label.campaignName"/>" value="${campaign.name}" />
									</li>
									<li>
										<textarea id="description" name="description" class="validate[required]" rows="3" placeholder="<spring:message code="label.campaignDescription" />">${campaign.description}</textarea>
									</li>
									<li>
										<label><spring:message code="label.selectMailingList" /></label>
										<form:select id="mlSelect" path="mailingList" class="uniform">
											<form:option value="0"><spring:message code="label.select"/></form:option>
											<form:options items="${mailingLists}" itemValue="id" itemLabel="name"/>
										</form:select>
									</li>
									<li id="mailingListDetails" hidden="true">
										<label><spring:message code="label.numberOfSubcribedClients"/>:&nbsp <span name="clientesNumber"></span></label>
										<div>
											<a data-toggle="modal" href="#modal-clientTable" class="btn btn-blue"><i class="icon-table"></i><spring:message code="label.seeClientList" /></a>
										</div>
									</li>
								</ul>
							<!-- </div> -->
							</div>
							<div class="form-actions">
								<div class="navbar-right">
									<a href="${pageContext.request.contextPath}/campaign/list" class="btn btn-default">
										<spring:message code="label.cancel" />
									</a>
									<button type="submit" class="btn btn-blue"><spring:message code="label.next" />&nbsp;&nbsp;<i class="icon-chevron-right"></i></button>
								</div>
							</div>
						</form:form>
					</div>
					<div id="step2Content" style="min-height: 500px;">
						<%@include file="campaignMessage.jsp"%>
					</div>
					<div id="step3Content" style="min-height: 500px;">
						<%@include file="campaignResult.jsp"%>
					</div>
				</div>
				<!-- <div class="text-center" style="margin-top:50px;"> -->
				<!-- <div class="text-center">
				  <ul class="pagination">
				    <li><a href="#">Prev</a></li>
				    <li><a href="#">Next</a></li>
				  </ul>
				</div> -->
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
    			<input id="campaignIdResult" type="hidden" name="id" value="${campaign.id}">
    			<input id="mailingList-id" type="hidden" name="mailingList.id" value="${campaign.mailingList.id}" id="selectedML">
	      		<div class="modal-header">
	        		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        		<h6 id="modal-tablesLabel"><spring:message code="label.sendEmails" /></h6>
	      		</div>
	    		<div class="modal-body padding">
	   				<span id="numberOfClients"></span>
	   				<label>
   						<i class="icon-warning-sign"></i> Esta campanha será enviada para <span name="clientesNumber"></span> cliente(s). Deseja continuar?
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
        	$("#step1li").addClass("active");
    		$("#step1Content").show();
    		$("#campaing-progress-bar").attr("style","width: 33%;");
    	});
    	
    	$("#step2").click(function(){
    		changeWizardStep();
        	$(this).addClass("active");
        	$("#step2li").addClass("active");
    		$("#step2Content").show();
        	$("#emailContent").cleditor({
    	   		height: 500
    	   	});
        	$("#campaing-progress-bar").attr("style","width: 66%;");
        	
        	updateCampaignResulStep();
    	});
    	
    	$("#step3").click(function(){
    		changeWizardStep();
        	$(this).addClass("active");
        	$("#step3li").addClass("active");
    		$("#step3Content").show();
    		$("#campaing-progress-bar").attr("style","width: 100%;");
    		
    		updateCampaignResulStep();
    	});
    	
    	function updateCampaignResulStep(){
    		var subject = $("#subject").val();
        	var mailingList = $("#mlSelect option:selected").text();
        	
        	$("#resultSubject").val(subject);
        	$("#resultSendTo").val(mailingList);
    	};
    	
    	function hideAllSteps(){
    		$("#step1Content").hide();
    		$("#step2Content").hide();
        	$("#step3Content").hide();
    	};
    	
    	function removeAllActiveClass(){
    		$("#step1").removeClass("active");
    		$("#step2").removeClass("active");
    		$("#step3").removeClass("active");
    		$("#step1li").removeClass("active");
    		$("#step2li").removeClass("active");
    		$("#step3li").removeClass("active");
    	};
    	
    	function changeWizardStep(){
    		hideAllSteps();
    		removeAllActiveClass();
    	};
    	
    	$(".imgCheck").click(function(){
    		var imgURL = $(this).attr("imgUrl");
    		alert(imgURL);
    	});
    	
    	$("#imagens").click(function(){
    		$("#thumbs").show();	
    	});
    	
    	function submitFormAndChangeStep(step)
    	{
    		var campId = $("#id").val();
    		var name = $("#name").val();
    		var description = $("#description").val();
    		var createdOn = $("#createdOn").val();
    		var emailContent = $("#emailContent").val();
    		var emailFileName = $("#emailFileName").val();
    		var mailingList = $("#mlSelect").val();
    		var submitted = $("#submitted").val();
    		var submittedDate = $("#submittedDate").val();
    		var statusId = $("#statusId").val();
    		var subject = $("#subject").val();
    		
    		$.ajax({
    	    	url: $("#campaingForm").attr("action"),
    	    	data: {
    	    			id : campId, 
    	    			name : name, 
	    	    		description : description,
    	    			createdOn : createdOn, 
	    	    		emailContent : emailContent,
    	    			emailFileName : emailFileName,
    	    			mailingList : mailingList,
    	        		submitted : submitted,
        				submittedDate : submittedDate,
        				statusId : statusId,
        				subject : subject
       				},
    	    	type: "POST",
    	    	success: function(campaignId) {
    	    		$("#id").val(campaignId);
    	    		$("#campaignIdResult").val(campaignId);
    	    		$("#mailingList-id").val(mailingList);
    	    		return Growl.success({
                        title:'Sucesso!',
                        text: 'Campanha salva com sucesso'
                    });
    	    	}
    	    });
    		
    		$(step).click();
    	}
    	
    	$("#campaingForm").submit(function( event ) {
    		event.preventDefault();
    		var mailingListId = $("#mlSelect").val();
    		if(mailingListId == 0)
    		{
    			return Growl.error({
                    title:'Erro!',
                    text: 'Selecione uma lista de e-mail'
                });
    		}else{
	    		submitFormAndChangeStep("#step2");
    		}
    	});
    	
    	if($("#mlSelect").val() > 0)
    	{
    		var mailingListId = $("#mlSelect").val();
    		populateMailingListData(mailingListId);
    	}
    	
    	$("#mlSelect").change(function(){
    		var mailingListId = $(this).val();
    		if(mailingListId > 0)
    		{
    			populateMailingListData(mailingListId);
    		}else
    		{
    			$("#mailingListDetails").hide();
    		}
    	});
    	
		$("#campaingFormStep2").submit(function( event ) {
			event.preventDefault();
    		submitFormAndChangeStep("#step3");
    		
    		var campaignContent = $("#emailContent").val();
    		$("#campaignResult").contents().find("body").html(campaignContent);
   		});
		
		function populateMailingListData(mailingListId)
    	{
    		$.get("${pageContext.request.contextPath}/campaign/getMailingListById/" + mailingListId, function(mailingList){
    			$("#mailingListDetails").show();
    			$("span[name='clientesNumber']").html(mailingList.clients.length);
    			$("#selectedML").val(mailingListId);
    			
    			if(mailingList.clients.length > 0){
	    			var rows = "<tr>";
	    			$.each(mailingList.clients, function(index, client){
	    				rows +='<td>' + client.name + '</td>';
	    				rows +='<td>' + client.email + '</td>';
	    				rows +='<td>' + client.clientType.name + '</td>';
	    				rows +='<td>' + client.formatedBirthday + '</td>';
	    				rows +='<td>' + client.formatedCreatedOn + '</td></tr>';
	    			});
	    			$('#clientsTable tbody').html(rows);
    			}
    		},"json");
    	}
		
		//using jquery.form.js
		function uploadJqueryForm(){
		   $("#formUploadImage").ajaxForm({
		    success:function(url) {
		    	var img = '<img src="' + url +'">';
		    	var content = $("#emailContent").val(); 
		    	    	
		    	$("#emailContent").val(content + "\n" + img);
				$("#emailContent").cleditor()[0].updateFrame();
		     },
		     dataType:"text"
		   }).submit();
		}
		
		$("#uploadImageBtn").click(function(event){
			event.preventDefault();
			uploadJqueryForm();
		});
 	});
 </script>