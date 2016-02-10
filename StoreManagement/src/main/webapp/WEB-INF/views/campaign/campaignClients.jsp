<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-md-12">
	<form:form id="campaingFormStep2" class="fill-up validatable" modelAttribute="campaign" action="#">
		<ul class="padded separate-sections">
			<li>
				<label><spring:message code="label.selectMailingList" /></label>
				<form:select id="mlSelect" path="mailingList" class="uniform">
					<form:option value="0"><spring:message code="label.select"/></form:option>
					<form:options items="${mailingLists}" itemValue="id" itemLabel="name"/>
				</form:select>
			</li>
			<li>
				<label></label>
			</li>
		</ul>
	</form:form>
</div>