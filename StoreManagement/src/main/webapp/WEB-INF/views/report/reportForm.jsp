<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-md-12">
		<form:form class="form-horizontal fill-up validatable" method="POST" commandName="reportVM" modelAttribute="reportVM" action="${pageContext.request.contextPath}/report/generate" >
			<div class="row">
				<div class="col-md-6">
					<ul class="padded separate-sections">
						<li>
							<label><spring:message code="label.selectDisiredReport"/></label>
							<form:select id="reportsSL" path="selectedReport" cssClass="uniform">
								<form:option value="0"><spring:message code="label.select"/></form:option>
								<form:option value="1"><spring:message code="report.resultOfMonth"/></form:option>
								<form:option value="2"><spring:message code="report.cumulativeResult"/></form:option>
								<form:option value="3"><spring:message code="report.evolutionOfIndicators"/></form:option> 
							</form:select>
						</li>
						<li id="storeFilter" hidden="hidden">
							<label><spring:message code="label.selectDisiredStore"/></label>
							<form:select path="store" items="${storeList}" itemValue="id" itemLabel="nameWithDesc" cssClass="uniform"/>
						</li>
						<li id="resultOfMonthFilter" hidden="hidden">
							<label><spring:message code="label.selectMonth"/></label>
							<div class="form-group"> 
								<label class="control-label col-md-1"><spring:message code="label.month" /></label>
								<div class="col-md-3">
									<form:select path="selectedMonth" cssClass="uniform validate[required]">
										<c:forEach var="month" items="${reportVM.monthList}">
											<form:option value="${month.code}"><spring:message code="${month.desc}"/></form:option>
										</c:forEach>
									</form:select>
								</div>
								<label class="control-label col-md-1"><spring:message code="label.year" /></label>
								<div class="col-md-2">
									<form:select path="selectedYear" cssClass="uniform validate[required]" items="${yearList}"></form:select>
								</div>
							</div>
						</li>
						
						<li id="otherFilters" hidden="hidden">
							<label><spring:message code="label.selectDisiredInterval"/></label>
							<div class="form-group"> 
								<label class="control-label col-md-2"><spring:message code="label.dateFrom"/></label>
								<div class="col-md-2">
									<form:select path="monthFrom" cssClass="uniform validate[required]" >
										<c:forEach var="month" items="${reportVM.monthList}">
											<form:option value="${month.code}"><spring:message code="${month.desc}"/></form:option>
										</c:forEach>
									</form:select>
								</div>
								<div class="col-md-2">													
									<form:select path="yearFrom" cssClass="uniform validate[required]" items="${yearList}"></form:select>
								</div>
								<label class="control-label col-md-2"><spring:message code="label.dateTo"/></label>
								<div class="col-md-2">
									<form:select path="monthTo" cssClass="uniform validate[required]" >
										<c:forEach var="month" items="${reportVM.monthList}">
											<form:option value="${month.code}"><spring:message code="${month.desc}"/></form:option>
										</c:forEach>
									</form:select>
								</div>
								<div class="col-md-2">
									<form:select path="yearTo" cssClass="uniform" items="${yearList}"></form:select>
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
		$("#reportsSL").change(function(){
			var reportCode = $(this).val();
			if(reportCode == 1)
			{
				$("#otherFilters").hide();
				$("#resultOfMonthFilter").show();
				$("#storeFilter").show();
			}else if(reportCode == 0)
			{
				$("#otherFilters").hide();
				$("#resultOfMonthFilter").hide();
				$("#storeFilter").hide();
			}else
			{
				$("#otherFilters").show();
				$("#resultOfMonthFilter").hide();
				$("#storeFilter").show();
			}
		});
	});
</script>
