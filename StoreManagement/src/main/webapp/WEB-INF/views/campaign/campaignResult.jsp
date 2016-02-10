<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-md-12">
	<div class="col-md-12">
		<form class="form-horizontal fill-up">
			<div class="padded">
				<div class="form-group">
					<label class="control-label col-lg-1">Para:</label>
					<div class="col-lg-11">
						<input id="resultSendTo" type="text" name="resultSendTo" value="${campaign.mailingList.name}" disabled="disabled">
					</div>
				</div>
				<!-- <div class="form-group">
					<label class="control-label col-lg-1">CC:</label>
					<div class="col-lg-11">
						<input id="resultSendToCC" type="text" name="resultSendToCC" value="">
					</div>
				</div> -->
				<div class="form-group">
					<label class="control-label col-lg-1"><spring:message code="label.subject" />:</label>
					<div class="col-lg-11">
						<input id="resultSubject" type="text" name="subject" value="${campaign.subject}" disabled="disabled">
					</div>
				</div>
			</div>
		</form>
		<iframe id="campaignResult" contenteditable="false" width="100%" height="700px"  style="border: medium groove;">
			${campaign.emailContent}
		</iframe>
	</div>
	<div class="form-actions">
		<button type="submit" class="btn btn-blue"><spring:message code="label.save" /></button>
		<a href="${pageContext.request.contextPath}/campaign/list" class="btn btn-default">
			<spring:message code="label.cancel" />
		</a>
		<div class="navbar-right">
			<a data-toggle="modal" href="#modal-warnSendEmails" class="btn btn-green"><spring:message code="label.sendEmails" /></a>
			<%-- <c:choose>
				<c:when test="${campaign.id > 0}">
					<a data-toggle="modal" href="#modal-warnSendEmails" class="btn btn-green"><spring:message code="label.sendEmails" /></a>
				</c:when>
				<c:otherwise>
					<a class="btn btn-red"><i class="icon-warn"></i><spring:message code="warning.saveTheCampaignToSendEmails"/></a>
				</c:otherwise>
			</c:choose> --%>
		</div>
	</div>
</div>