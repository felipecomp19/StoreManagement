<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-md-12">
		<form:form class="fill-up" method="POST" commandname="account" action="${pageContext.request.contextPath}/account/save" >
			<div class="row">
				<div class="col-lg-4">
					<ul class="padded separate-sections">
						<c:if test="${successfull ne null}">
							<c:choose>
								<c:when test="${successfull}">
									<li>
										<div class="alert alert-success">
										  <button type="button" class="close" data-dismiss="alert">×</button>
										  <spring:message code="message.successUpdate"/>
										</div>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<div class="alert alert-error">
										  <button type="button" class="close" data-dismiss="alert">×</button>
										  <spring:message code="message.errorUpdate"/>
										</div>
									</li>
								</c:otherwise>
							</c:choose>
						</c:if>
						<li class="input">
							<input type="hidden" name="id" value="${account.id}"/>
							<label><spring:message code="label.name" /></label>
							<input type="text" name="name" placeholder="<spring:message code="label.name"/>" value="${account.name}"/>
							<span class="help-block note"><i class="icon-warning-sign"></i><spring:message code="warning.accountName"/></span>
						</li>
					</ul>
				</div>
				<div class="col-lg-8">
					<ul class="padded separate-sections">
					<li>
					<div class="box">
						<div class="box-header">
						    <span class="title"><spring:message code="label.payments"/></span>
						    <ul class="box-toolbar">
						    	<li>
						      		<span class="label label-green">
						      			<spring:message code="label.plan"/>&nbsp;<spring:message code="${account.plan.name}"/>
						      		</span>
			      				</li>
						    </ul>
					  	</div>
					  	<div class="box-content">
						    <table class="table table-normal">
							      <thead>
							      <tr>
							        <td style="width: 40px"></td>
							        <td><spring:message code="label.date"/></td>
							        <td><spring:message code="label.value"/></td>
							        <td><spring:message code="label.status"/></td>
							      </tr>
							      </thead>
							      <tbody>
							      	<c:forEach var="payment" items="${account.payments}">
							      		<tr>
							      			<td></td>
							      			<td>${payment.date}</td>
							      			<td>${payment.value}</td>
							      			<td>Status</td>
							      		</tr>
							      	</c:forEach>
							      </tbody>
						      </table>
					     </div>
					</div>
					</li>
					</ul>
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-blue"><spring:message code="label.save" /></button>
				<a href="${pageContext.request.contextPath}/dashboard" class="btn btn-default">
					<spring:message code="label.cancel" />
				</a>
			</div>
		</form:form>
	</div>
</div>