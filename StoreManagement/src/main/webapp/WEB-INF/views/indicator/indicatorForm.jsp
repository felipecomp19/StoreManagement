<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-md-12">
		<form:form class="fill-up validatable" method="POST" commandName="indicator" modelAttribute="indicator" action="${pageContext.request.contextPath}/indicator/save" >
			<div class="row">
				<div class="col-md-3">
					<ul class="padded separate-sections">
						<li>
							<h4><spring:message code="label.indicators" /></h4>
							<form:hidden path="id"/>
						</li>
						<li>
							<label><spring:message code="label.stores"/></label>
							<form:select path="store" items="${stores}" itemValue="idAsString" itemLabel="nameWithDesc" class="chzn-select"></form:select>		
						</li>
						<li>
							<label><spring:message code="label.employee"/></label>
							<form:select path="employee" items="${employees}" itemValue="idAsString" itemLabel="name" class="chzn-select"></form:select>		
						</li>
						<li>
							<div class="row">
							<div class="col-md-6">
								<label><spring:message code="label.month"/></label>
								<form:select path="month" class="chzn-select">
									<form:option value="1"><spring:message code="month.jan"/></form:option>
									<form:option value="2"><spring:message code="month.feb"/></form:option>
									<form:option value="3"><spring:message code="month.mar"/></form:option>
									<form:option value="4"><spring:message code="month.apr"/></form:option>
									<form:option value="5"><spring:message code="month.may"/></form:option>
									<form:option value="6"><spring:message code="month.jun"/></form:option>
									<form:option value="7"><spring:message code="month.jul"/></form:option>
									<form:option value="8"><spring:message code="month.ago"/></form:option>
									<form:option value="9"><spring:message code="month.set"/></form:option>
									<form:option value="10"><spring:message code="month.oct"/></form:option>
									<form:option value="11"><spring:message code="month.nov"/></form:option>
									<form:option value="12"><spring:message code="month.dez"/></form:option>
								</form:select>
							</div>
							<div class="col-md-6">
								<label><spring:message code="label.year"/></label>
								<form:select path="year" items="${years}" class="chzn-select"></form:select>
							</div>
							</div>
						</li>
					</ul>
				</div>
				<div class="col-md-4">
					<ul class="padded separate-sections form-horizontal">
						<li>
							<h4><spring:message code="label.indicators" /></h4>
						</li>
						<li class="input">
							<div class="form-group" align="left">
								<label class="control-label col-lg-5" style="text-align: left"><spring:message code="label.workedDays"/></label>
								<div class="col-lg-3">
									<form:input path="workedDays" maxlength="2" />
								</div>
							</div>
						</li>
						
						<li class="input">
							<div class="form-group" align="left">
								<label class="control-label col-lg-5" style="text-align: left"><i class="icon-money"></i>&#32&#32<spring:message code="label.goal"/></label>
								<div class="col-lg-3">
									<form:input path="goal" class="money" data-affixes-stay="true" data-symbol="R$ " data-thousands="." data-decimal=","/>
								</div>
								<span class="help-block note"><i class="icon-warning-sign"></i><spring:message code="warning.typeJustNumbers"/></span>
							</div>
						</li>
						
						<li class="input">
							<div class="form-group" align="left">
								<label class="control-label col-lg-5" style="text-align: left"><i class="icon-money"></i>&#32&#32<spring:message code="label.valueOfSales"/></label>
								<div class="col-lg-3">
									<form:input path="valueOfSales" class="money" data-affixes-stay="true" data-symbol="R$ " data-thousands="." data-decimal=","/>
								</div>
								<span class="help-block note"><i class="icon-warning-sign"></i><spring:message code="warning.typeJustNumbers"/></span>
							</div>
						</li>
						<li class="input">
							<div class="form-group" align="left">
								<label class="control-label col-lg-5" style="text-align: left"><spring:message code="label.numberOfAttendances"/></label>
								<div class="col-lg-3">
									<form:input path="numberOfAttendances" maxlength="5"/>
								</div>
							</div>
						</li>
						<li class="input">
							<div class="form-group" align="left">
								<label class="control-label col-lg-5" style="text-align: left"><spring:message code="label.numberOfSales"/></label>
								<div class="col-lg-3">
									<form:input path="numberOfSales" maxlength="5"/>
								</div>
							</div>
						</li>
						<li class="input">
							<div class="form-group" align="left">
								<label class="control-label col-lg-5" style="text-align: left"><spring:message code="label.numberOfItemsSold"/></label>
								<div class="col-lg-3">
									<form:input path="numberOfItemsSold" maxlength="5"/>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-blue"><spring:message code="label.save" /></button>
				<a href="${pageContext.request.contextPath}/indicator/list">
					<button type="button" class="btn btn-default"><spring:message code="label.cancel" /></button>
				</a>
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function () { 
		// Configuração para campos de Real.
		$(".money").maskMoney();
		$(".money").maskMoney().maskMoney('mask');
	});
</script>
