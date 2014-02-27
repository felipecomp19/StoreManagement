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
						<span class="breadcrumb-label"> <i class="icon-picture"></i> <spring:message code="label.imagens" />
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
							<span class="title"><spring:message code="label.imagens" /></span>
							<ul class="box-toolbar">
								<li>
									<a href="${pageContext.request.contextPath}/imagens/upload">
									<button class="btn btn-green">
										<i class="icon-picture"></i>&nbsp&nbsp<spring:message code="label.uploadImagens" />
									</button>
									</a>
								</li>
							</ul>
						</div>
						<div class="box-content">
							<div id="thumbs">
								<c:forEach var="img" items="${imagens}">
										<a href="${relativePath}${img.fileName}" style="background-image:url(${relativePath}${img.fileName})" title="${img.name}"></a>
										<button id="copyURL" url="http://localhost:8080${relativePath}${img.fileName}" class="btn btn-xs btn-green"><spring:message code="label.copy" /></button>
										<a href="${pageContext.request.contextPath}/imagens/delete/${client.id}">
											<button class="btn btn-xs btn-red"><spring:message code="label.delete" /></button>
										</a>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</jsp:body>
</t:template>
