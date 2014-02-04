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
			
			<!-- 
			<button id="btnNewStore" class="btn btn-blue"><spring:message code="label.newStore"/></button>
			
			<hr class="divider" style="margin-top: 10px">
			
			<div class="row">
			<div class="col-md-8">
				<div class="box">
					<div class="box-header">
						<span class="title"><spring:message code="label.newStore" /></span>
					</div>
					<div class="box-content">
						<form class="fill-up">
							<div class="row">
								<div class="col-lg-6">
									<ul class="padded separate-sections">
						                <li class="input">
						                  <input type="text"
													placeholder="<spring:message code="label.name"/>" />
						                </li>
						                <li class="input">
						                	<input type="text"
													placeholder="<spring:message code="label.description"/>" />
						                </li>
						                <li class="input">
						                  <input type="text"
													placeholder="<spring:message code="label.telephone"/>" />
						                </li>
	                				</ul> 
								</div>
								<div class="col-lg-4">
									<ul class="padded separate-sections">
										<li class="input">
						                  <input type="text"
													placeholder="<spring:message code="label.cep"/>" />
						                </li>
										<li class="input">
						                  <input type="text"
													placeholder="<spring:message code="label.address"/>" />
						                </li>
						                <li class="input">
						                  <input type="text"
													placeholder="<spring:message code="label.city"/>" />
						                </li>
									</ul>
								</div>
								<div class="col-lg-2">
									<ul class="padded separate-sections">
										<li class="input">
						                	<input type="text"
													placeholder="<spring:message code="label.number"/>" />
						                </li>
	            		                <li class="input">
						                	<input type="text"
													placeholder="<spring:message code="label.state"/>" />
						                </li>
									</ul>
								</div>
							</div>
						</form>
					</div>
					</div>
			</div>
			</div>
			 -->
			<c:forEach var="store" items="${stores}">
				<p>${stores.size}</p>
				<p>${store.name}</p>
			</c:forEach>				
		</div>
		<!-- [END] main content -->
	</jsp:body>
</t:template>