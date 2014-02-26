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
						<span class="breadcrumb-label"> <i class="icon-list-alt"></i> <spring:message code="label.mailingLists" />
						</span> <span class="breadcrumb-arrow"><span></span></span>
					</div>
				</div>
			</div>
		</div>
		<!-- [END] Breadcrumb -->
		
		<!-- main content -->
		<div class="container">
			<div class="box">
				<div class="box-header">
					<span class="title"><spring:message code="label.mailingLists" /></span>
					<ul class="box-toolbar">
						<li>
							<a href="${pageContext.request.contextPath}/mailingList/create">
							<button class="btn btn-green">
								<i class="icon-building"></i>&nbsp&nbsp<spring:message code="label.newMailingList" />
							</button>
							</a>
						</li>
					</ul>
				</div><!-- box-header -->
				<div class="box-content">
					<div id="dataTables">
						<table cellpadding="0" cellspacing="0" border="0" class="dTable responsive">
							<thead>
								<tr>
									<th style="width:50px;"></th>
									<th>
										<spring:message code="label.name" />
									</th>
									<th>
										<spring:message code="label.createdOn" />
									</th>
									<th>
										<spring:message code="label.subscribers" />
									</th>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach var="ml" items="${mailingLists }">
									<tr>
										<td class="center"> 
											<div class="btn-group">
												<button class="btn btn-xs btn-default dropdown-toggle"
													data-toggle="dropdown">
													<i class="icon-cog"></i>
												</button>
												<ul class="dropdown-menu">
													<li>
														<a href="${pageContext.request.contextPath}/mailingList/edit/${ml.id}"><spring:message code="label.edit"/></a>
													</li>
													<li class="divider" />
													<li>
														<a href="${pageContext.request.contextPath}/mailingList/delete/${ml.id}"><spring:message code="label.delete"/></a>
													</li>
												</ul>
											</div>
										</td>
										<td><a href="${pageContext.request.contextPath}/mailingList/edit/${ml.id}">${ml.name}</a></td>
										<td>${ml.formatedCreatedOn}</td>
										<td>${ml.clientsListSize}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- [END] main content -->

	</jsp:body>
</t:template>