<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-md-6">
		<form:form class="fill-up validatable" method="POST" commandName="user" action="${pageContext.request.contextPath}/user/save" >
			<div class="row">
				<div class="col-md-6">
					<ul class="padded separate-sections">
						<li class="input">
							<input type="hidden" name="id" value="${user.id}"/>
							<label><spring:message code="label.role" /></label>
							<form:select path="userRole" items="${roles}" itemValue="id" itemLabel="role" class="uniform validate[required]"/>
						</li>
						<li class="input">
							<label><spring:message code="label.name" /></label>
							<input type="text" name="name" class="validate[required]" placeholder="<spring:message code="label.name"/>" value="${user.name}"/>
						</li>
						<li class="input">
							<label><spring:message code="label.userName" /></label>
							<div class="input-group addon-left">
								<span class="input-group-addon" href="#">
            						<i class="icon-user"></i>
          						</span>
								<input type="text" name="userName" id="userName" autocomplete="off" class="validate[required]" placeholder="<spring:message code="label.userName"/>" value="${user.userName}"/>
							</div>
						</li>
						<li class="input">
							<label><spring:message code="label.password" /></label>
							<div class="input-group addon-left">
				          		<span class="input-group-addon" href="#">
						        	<i class="icon-key"></i>
						        </span>
								<input type="password" name="password" id="password" autocomplete="off" class="validate[required]" placeholder="<spring:message code="label.password"/>" value=""/>
							</div>
						</li>
						<li class="input">
							<label><spring:message code="label.confirmPassword" /></label>
							<div class="input-group addon-left">
          						<span class="input-group-addon" href="#">
            						<i class="icon-key"></i>
          						</span>
								<input type="password" name="confirmPassword" id="confirmPassword" class="validate[required]" placeholder="<spring:message code="label.confirmPassword"/>" value=""/>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-blue"><spring:message code="label.save" /></button>
				<a href="${pageContext.request.contextPath}/user/list">
					<button type="button" class="btn btn-default"><spring:message code="label.cancel" /></button>
				</a>
			</div>
		</form:form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function () {
		$("form").validate({
            rules : {
            	password : {
                    minlength : 5
                },
                confirmPassword : {
                    minlength : 5,
                    equalTo : "#password"
                },
                messages: {
                	password: {
                        minlength: "Sua senha deve ter pelo menos 5 caracteres"
                    },
                    confirmPassword: {
                        minlength: "Sua senha deve ter pelo menos 5 caracteres",
                        equalTo: "Senha e confirmação de senha não conferem."
                    }
                }
            }
		});
	});
</script>
