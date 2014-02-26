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
					<label>Número de clientes inscritos: <span id="clientesNumber"></span></label>
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
        		<button class="btn btn-default" data-dismiss="modal">Close</button>
      		</div>
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
    	
    	$("#mlSelect").change(function(){
    		var mailingListId = $(this).val();
    		if(mailingListId > 0)
    		{
	    		$.get("${pageContext.request.contextPath}/campaign/getMailingListById/" + mailingListId, function(mailingList){
	    			$("#mailingListDetails").show();
	    			$("#clientesNumber").html(mailingList.clients.length);
	    			
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
    		}else
    		{
    			$("#mailingListDetails").hide();
    		}
    	});
    	
    	$("#seeClientList").click(function(event){
    		event.preventDefault();
    		alert("show dialog");
    	});
 	});
 </script>