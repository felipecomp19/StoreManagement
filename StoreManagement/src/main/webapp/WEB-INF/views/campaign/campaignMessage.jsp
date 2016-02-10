<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form:form id="campaingFormStep2" method="POST" modelAttribute="campaign" action="${pageContext.request.contextPath}/campaign/save" class="form-horizontal fill-up">
	<div class="col-md-12">
		<div class="box">
			<div class="box-header">
				<div class="title">Email</div>
				<ul class="box-toolbar">
		          <li class="toolbar-link">
		            <a id="addImage" data-toggle="modal" href="#modal-addImage">
		              <i class="icon-picture"></i> <spring:message code="label.addImagen"/>
		            </a>
		          </li>
		        </ul>
			</div>
			<div class="box-content">
				<div class="col-md-12">
					<div class="padded">
						<%-- <div class="form-group">
							<label class="control-label col-lg-1">Para:</label>
							<div class="col-lg-11">
								<input id="resultSendTo" type="text" name="resultSendTo" value="${campaign.mailingList.name}" disabled="disabled">
							</div>
						</div> --%>
						<!-- <div class="form-group">
							<label class="control-label col-lg-1">CC:</label>
							<div class="col-lg-11">
								<input id="resultSendToCC" type="text" name="resultSendToCC" value="">
							</div>
						</div> -->
						<div class="form-group">
							<label class="control-label col-lg-1"><spring:message code="label.subject" />:</label>
							<div class="col-lg-11">
								<input id="subject" type="text" name="subject" class="validate[required]" value="${campaign.subject}">
							</div>
						</div>
					</div>
				
					<%-- <ul class="padded separate-sections">
						<li class="input">
							<label><spring:message code="label.subject"/></label>
							<input id="subject" type="text" name="subject" class="validate[required]" value="${campaign.subject}">
						</li>
					</ul> --%>
					<textarea id="emailContent" name="emailContent" >${campaign.emailContent}<img src="http://www.stmanager.com.br/storeManager/tutti/images/4aaa.jpg"></textarea>
				</div>
				
			</div>
		</div>
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

<div id="modal-addImage" class="modal fade">
	<div class="modal-dialog">
    	<div class="modal-content">
      		<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        		<h6 id="modal-tablesLabel"><spring:message code="label.uploadImagens" /></h6>
      		</div>
    		<form:form id="formUploadImage" class="fill-up validatable" method="post" action="${pageContext.request.contextPath}/imagens/uploadImagensAjax" commandName="uploadForm" modelAttribute="uploadForm" enctype="multipart/form-data">
    			<div class="modal-body nopadding">
					<div class="row">
						<div class="col-md-12">
						<ul class="padded separate-sections">
							<li class="input">
								<label><spring:message code="label.name"/></label>
								<input type="text" name="name" class="validate[required]">
							</li>
							<li class="input">
								<label><spring:message code="label.selectImage"/></label>
								<input name="files[0]" type="file" />
						   	</li>
					   	   	<li class="clearfix">
						   		<div class="note pull-right"><spring:message code="warning.pleaseSelectAnImagenFile" /></div>
					   		</li>
						</ul>
						</div>
					</div>
				</div>
				<div class="modal-footer">
		       		<button id="uploadImageBtn" value="Submit" class="btn btn-blue" data-dismiss="modal"><spring:message code="label.upload" /></button>
					<button class="btn btn-default" data-dismiss="modal"><spring:message code="label.cancel"/></button>
	     		</div>
      		</form:form>
		</div>
	</div>
</div>