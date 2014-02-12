<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>

<t:template>
<jsp:body>
	<!-- top main content -->
	<%@include file="topMainContent.jsp"%>
	<!-- [END] top main content -->
	
	<!-- Breadcrumb -->
	<div class="container padded">
		<div class="row">
			<div id="breadcrumbs">
				<div class="breadcrumb-button blue">
					<span class="breadcrumb-label"><i class="icon-home"></i>Home</span>
					<span class="breadcrumb-arrow"><span></span></span>
				</div>
				<div class="breadcrumb-button">
					<span class="breadcrumb-label"> <i class="icon-group"></i> <spring:message
							code="label.clients" />
					</span> <span class="breadcrumb-arrow"><span></span></span>
				</div>
				<div class="breadcrumb-button">
					<span class="breadcrumb-label"> <i class="icon-folder-open-alt"></i> <spring:message code="label.uploadClientList" />
					</span> <span class="breadcrumb-arrow"><span></span></span>
				</div>
			</div>
		</div>
	</div>
	<!-- [END] Breadcrumb -->
		
	<!-- main content -->
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header">
						<span class="title"><spring:message code="label.form" /></span>
					</div>
					<div class="box-content">
						<form:form class="fill-up" method="post" action="${pageContext.request.contextPath}/client/uploadClients" modelAttribute="uploadForm" enctype="multipart/form-data">
							<div class="row">
								<ul class="padded separate-sections">
									<li class="input">
										<label><spring:message code="label.uploadClientList"/></label>
										<input name="files[0]" type="file" />
								   	</li>
							   	   	<li class="clearfix">
								   		<div class="note pull-right"><spring:message code="warning.pleaseSelectAnExcelFile" /></div>
							   		</li>
								</ul>
							</div>
							<!-- <input type="submit" value="Upload" /> -->
							<div class="form-actions">
								<button type="submit" class="btn btn-blue"><spring:message code="label.upload" /></button>
								<a href="${pageContext.request.contextPath}/client/list">
									<button type="button" class="btn btn-default"><spring:message code="label.cancel" /></button>
								</a>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- [END] main content -->
	
</jsp:body>
</t:template>