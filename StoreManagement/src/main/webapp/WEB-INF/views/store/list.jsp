<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<t:template>
	<jsp:body>
		
		<!-- top main content -->
		<div class="container">
			<div class="row">
				<div class="area-top clearfix">
					<div class="pull-left header">
						<h3 class="title">
							<i class="icon-building"></i> <spring:message code="label.stores" />
						</h3>
						<h5>
							<span> A subtitle can go here </span>
						</h5>
					</div>
					<ul class="list-inline pull-right sparkline-box">
						<li class="sparkline-row">
							<h4 class="blue">
								<span><spring:message code="label.totalOfStores"/></span> ${totalOfStores}
							</h4>
							<div class="sparkline big" data-color="blue">
								<!--1,11,5,28,25,19,27,6,4,23,20,6-->
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- [END] top main content -->
		
		<!-- Breadcrumb -->		
		<div class="container padded">
			<div class="row">
				<div id="breadcrumbs">
					<div class="breadcrumb-button blue">
						<span class="breadcrumb-label"><i class="icon-home"></i>
							Home</span> <span class="breadcrumb-arrow"><span></span></span>
					</div>
					<div class="breadcrumb-button">
						<span class="breadcrumb-label"> <i class="icon-building"></i>
							<spring:message code="label.stores" />
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
							<span class="title"><spring:message code="label.stores" /></span>
							<ul class="box-toolbar">
								<li>
									<a href="newStore">
									<button class="btn btn-green">
										<i class="icon-building"></i>&nbsp&nbsp<spring:message code="label.newStore" />
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
											<th><div><spring:message code="label.name" /></div></th>
											<th><div><spring:message code="label.description"/></div></th>
											<th><div><spring:message code="label.telephone"/></div></th>
											<th><div><spring:message code="label.numberOfClients" /></div></th>
											<th><div><spring:message code="label.actions" /></div></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="store" items="${stores}">
											<tr>
												<td>${store.name}</td>
												<td>${store.description}</td>
												<td>${store.telephone}</td>
												<td class="center">3</td>
												<td class="center"> 
													<div class="btn-group">
														<button class="btn btn-xs btn-default dropdown-toggle" data-toggle="dropdown">
															<i class="icon-cog"></i>
														</button>
														<ul class="dropdown-menu">
															<li>
																<a href="edit/${store.id}">Edit</a>
															</li>
															<li class="divider"/>
															<li>
																<a href="delete/${store.id}">Delete</a>
															</li>
														</ul>
													</div>
												</td>
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