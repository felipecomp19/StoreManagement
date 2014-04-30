<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-md-12">
		<form:form id="userForm" class="fill-up validatable" method="POST" modelAttribute="user" commandName="user" action="${pageContext.request.contextPath}/user/save" >
			<div class="row">
				<div class="col-md-3">
					<ul class="padded separate-sections">
						<li><h4><spring:message code="label.stores"/></h4></li>
						<li>
							<div id="storesCB">
								<ul  class="padded separate-sections">
									<sec:authorize ifAnyGranted="ROLE_MANAGER,ROLE_ADMIN">
										<form:checkboxes id="stores" items="${stores}" path="stores" itemValue="idAsString" itemLabel="nameWithDesc" class="icheck break-word validate[required]" element="li" />
									</sec:authorize>
									<sec:authorize ifAnyGranted="ROLE_USER">
										<form:checkboxes disabled="true" id="stores" items="${stores}" path="stores" itemValue="idAsString" itemLabel="nameWithDesc" class="icheck break-word validate[required]" element="li" />
									</sec:authorize>
								</ul>
							</div>
						</li>
					</ul>
				</div>
				<div class="col-md-4">
					<ul class="padded separate-sections">
						<li class="input">
							<input type="hidden" name="id" value="${user.id}"/>
							<sec:authorize ifAnyGranted="ROLE_MANAGER,ROLE_ADMIN">
								<label><spring:message code="label.role" /></label>
								<form:select id="userRole" path="userRole" items="${roles}" itemValue="id" itemLabel="roleTranslated" class="uniform"/>
							</sec:authorize>
							<sec:authorize ifAnyGranted="ROLE_USER">
								<label><spring:message code="label.role" /></label>
								<form:select disabled="true" id="userRole" path="userRole" items="${roles}" itemValue="id" itemLabel="roleTranslated" class="uniform"/>
							</sec:authorize>
						</li>
						<li class="input">
							<label><spring:message code="label.name" /></label>
							<input type="text" name="name" class="validate[required]" placeholder="<spring:message code="label.name"/>" value="${user.name}"/>
						</li>
						<li class="input">
							<form:checkbox path="active" class="icheck" id="active"/>
							<label for="active"><spring:message code="label.enabled" /></label>
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
								<input type="password" name="password" id="password" autocomplete="off" placeholder="<spring:message code="label.password"/>" value=""/>
							</div>
						</li>
						<li class="input">
							<label><spring:message code="label.confirmPassword" /></label>
							<div class="input-group addon-left">
          						<span class="input-group-addon" href="#">
            						<i class="icon-key"></i>
          						</span>
								<input type="password" name="confirmPassword" id="confirmPassword" placeholder="<spring:message code="label.confirmPassword"/>" value=""/>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-blue"><spring:message code="label.save" /></button>
				<a href="${pageContext.request.contextPath}/user/list" class="btn btn-default">
					<spring:message code="label.cancel" />
				</a>
			</div>
		</form:form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function () {
		$("#userForm").validate({
			errorPlacement: function(error, element) { },
            rules : {
            	password : {
                    minlength : 5
                },
                confirmPassword : {
                    minlength : 5,
                    equalTo : "#password"
                }
            },
            messages: {
            	password: {
                    minlength: function(){ return Growl.error({
                        						title:'Erro!',
                        						text: 'Sua senha deve ter pelo menos 5 caracteres'
                   							});
                    			}
                },
                confirmPassword: {
                    minlength: function(){ return Growl.error({
												title:'Erro!',
												text: 'Sua senha deve ter pelo menos 5 caracteres'
										 	});
					},
                    equalTo: function(){ return Growl.error({
												title:'Erro!',
												text: 'Senha e confirmação de senha não conferem.'
				 							});
                 	}
                }
            }
		});
	});
</script>
