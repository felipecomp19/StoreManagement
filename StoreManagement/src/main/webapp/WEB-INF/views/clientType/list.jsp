<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
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
						<span class="breadcrumb-label"> <i class="icon-group"></i> <spring:message code="label.clients" />
						</span> <span class="breadcrumb-arrow"><span></span></span>
					</div>
					<div class="breadcrumb-button">
						<span class="breadcrumb-label"> <i class="icon-user"></i>
							<spring:message code="label.clientTypes" />
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
							<span class="title"><spring:message code="label.clientTypes" /></span>
							<ul class="box-toolbar">
								<li>
									<a href="${pageContext.request.contextPath}/clientType/create">
									<button class="btn btn-green">
										<i class="icon-building"></i>&nbsp&nbsp<spring:message code="label.newClientType" />
									</button>
									</a>
								</li>
							</ul>
						</div>
						<div class="box-content">
							<div id="dataTables">
								<table cellpadding="0" cellspacing="0" border="0" class="dTable responsive">
									<thead>
										<tr>
											<th style="width:50px;"></th>
											<th><div><spring:message code="label.name" /></div></th>
											<th><div><spring:message code="label.description"/></div></th>
											<th><div><spring:message code="label.numberOfClients" /></div></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="cliType" items="${cliTypes}">
											<tr>
												<td class="center"> 
													<div class="btn-group">
														<button class="btn btn-xs btn-default dropdown-toggle" data-toggle="dropdown">
															<i class="icon-cog"></i>
														</button>
														<ul class="dropdown-menu">
															<li>
																<a href="edit/${cliType.id}"><spring:message code="label.edit"/></a>
															</li>
															<li class="divider"/>
															<li>
																<a href="delete/${cliType.id}"><spring:message code="label.delete"/></a>
															</li>
														</ul>
													</div>
												</td>
												<td>${cliType.name}</td>
												<td>${cliType.description}</td>
												<td class="center">${cliType.clientsSize}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- [END] main content -->
	</jsp:body>
</t:template>