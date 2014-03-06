<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form:form id="campaingForm" class="fill-up validatable" method="POST" modelAttribute="campaign" action="${pageContext.request.contextPath}/campaign/save">
	<div class="row">
		<div class="col-md-6">
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
		</div>
		<div class="col-md-6">
			<ul class="padded separate-sections">
				<li>
					<label><spring:message code="label.selectMailingList" /> </label>
					<form:select id="mlSelect" path="mailingList" class="uniform">
						<form:option value="0">Selecione uma lista de e-mail</form:option>
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
		</div>
	</div>	
	<div class="row">
		<div class="col-md-12">
			<ul class="padded separate-sections">
				<li>
					<h4>Email</h4>
				</li>
				<li>
					<textarea id="emailContent" name="emailContent" >${campaign.emailContent}</textarea>
				</li>
			</ul>
		</div>
	</div>
	<div class="form-actions">
		<button type="submit" class="btn btn-blue"><spring:message code="label.save" /></button>
		<a href="${pageContext.request.contextPath}/campaign/list">
			<button type="button" class="btn btn-default"><spring:message code="label.cancel" /></button>
		</a>
		<div class="navbar-right">
			<c:choose>
				<c:when test="${campaign.id > 0}">
					<a data-toggle="modal" href="#modal-warnSendEmails" class="btn btn-green"><spring:message code="label.sendEmails" /></a>
				</c:when>
				<c:otherwise>
					<a class="btn btn-red"><i class="icon-warn"></i><spring:message code="warning.saveTheCampaignToSendEmails"/></a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</form:form>

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
    	$("#emailContent").cleditor({
    		height: 700
    	});
    	
    	$("#campaingForm").submit(function(event){
    		var mailingListId = $("#mlSelect").val();
    		if(mailingListId == 0)
    		{
    			event.preventDefault();
    			return Growl.error({
                    title:'Erro!',
                    text: 'Selecione uma lista de e-mail'
                });
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
    	
    	$("#seeClientList").click(function(event){
    		event.preventDefault();
    		alert("show dialog");
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
 	});
 </script>