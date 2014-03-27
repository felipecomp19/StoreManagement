<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-md-12">
		<form:form class="fill-up validatable" method="POST" commandName="reportVM" modelAttribute="reportVM" action="${pageContext.request.contextPath}/report/generate" >
			<div class="row">
				<div class="col-md-6">
					<ul class="padded separate-sections">
						<li>Selecione o relatorio desejado</li>
						<li>
							<form:select path="selectedReport" cssClass="uniform">
								<form:option value="0"><spring:message code="label.select"/></form:option>
								<form:option value="1"><spring:message code="report.resultOfMonth"/></form:option>
								<form:option value="2"><spring:message code="report.cumulativeResult"/></form:option>
								<form:option value="3"><spring:message code="report.evolutionOfIndicators"/></form:option>
							</form:select>
						</li>
						
						<%-- <li><spring:message code="label.selectMonth"></spring:message></li>
						<li>
							<div class="col-md-3">
								<form:select id="selectedMonth" path="selectedMonth" class="uniform">
									<form:option value="month.jan"><spring:message code="month.jan"/></form:option>
									<form:option value="2"><spring:message code="month.feb"/></form:option>
									<form:option value="3"><spring:message code="month.mar"/></form:option>
									<form:option value="4"><spring:message code="month.apr"/></form:option>
									<form:option value="month.may"><spring:message code="month.may"/></form:option>
									<form:option value="6"><spring:message code="month.jun"/></form:option>
									<form:option value="7"><spring:message code="month.jul"/></form:option>
									<form:option value="8"><spring:message code="month.ago"/></form:option>
									<form:option value="9"><spring:message code="month.set"/></form:option>
									<form:option value="10"><spring:message code="month.oct"/></form:option>
									<form:option value="11"><spring:message code="month.nov"/></form:option>
									<form:option value="12"><spring:message code="month.dez"/></form:option>
								</form:select> 
							</div>
						</li> --%>
						<li>
							<div class="row action-nav-row">
								<div class="col-md-1" align="right">
									<label class="control-label"><spring:message code="label.year" /></label>
								</div>
								<div class="col-md-3">
									<form:select path="selectedYear" class="uniform">
										<form:option value="2013">2013</form:option>
										<form:option value="2014">2014</form:option>
										<form:option value="2015">2015</form:option>
										<form:option value="2016">2016</form:option>
									</form:select> 
								</div>
								<div class="col-md-1" align="right" style="vertical-align: bottom;">
									<label class="control-label"><spring:message code="label.selectMonth" /></label>
								</div>
								<div class="col-md-3">
									<form:select id="selectedMonth" path="selectedMonth" class="uniform">
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
							</div>
						</li>
						
					</ul>
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-blue"><spring:message code="label.generate" /></button>
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function () { 
		
	});
</script>
