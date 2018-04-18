
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<c:url var="submitRegistrationUrl" value="/signup" />
<spring:message var="pageTitle" code="newUserRegistration.pageTitle" />
<spring:message var="userNameLabel" code="newUserRegistration.label.username" />
<spring:message var="passwordLabel" code="newUserRegistration.label.password" />
<spring:message var="register" code="newUserRegistration.label.register" />
<spring:message var="lastNameLabel" code="newUserRegistration.label.lastname" />
<spring:message var="firstNameLabel" code="newUserRegistration.label.firstname" />
<spring:message var="emailLabel" code="newUserRegistration.label.email" />

<!DOCTYPE html>
<html>
	<head>
	    <title>Sign Up</title>
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.css" />
	    <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	    <script src="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.js"></script>

	</head>
  
	<body>

		<h1><c:out value="${pageTitle}" /></h1>
		<div style="color: #ff0000;">
			<h2><c:out value="${duplicateIdEmail}" /></h2>
		</div>
		
		<form:form action="${submitRegistrationUrl}" modelAttribute="users" data-ajax="false">
                    <p><spring:message code="newUserRegistration.message.allFieldsRequired" /></p>
                    
                    <form:label path="userName">${userNameLabel}</form:label>
                    <form:errors path="userName" cssStyle="color: #ff0000;" />
                    <form:input path="userName"  placeholder="Enter user name" data-theme="d" />
					
					<form:label path="password">${passwordLabel}</form:label>
                    <form:errors path="password" cssStyle="color: #ff0000;"/>
                    <form:password path="password" placeholder="Enter password" data-theme="d" />
                    
                    <form:label path="confirmedPassword">Confirm Password</form:label>
                    <form:errors path="confirmedPassword" cssStyle="color: #ff0000;"/>
                    <form:password path="confirmedPassword" placeholder="Enter confirm password" data-theme="d" />      
					
					<form:label path="lastName">${lastNameLabel}</form:label>
					<form:errors path="lastName" cssStyle="color: #ff0000;"/>
                    <form:input path="lastName" placeholder="Enter last name" data-theme="d"/>
					
					<form:label path="firstName">${firstNameLabel}</form:label>
					<form:errors path="firstName" cssStyle="color: #ff0000;"/>
                    <form:input path="firstName" placeholder="Enter first name" data-theme="d"/>
                    
                    <form:label path="email">${emailLabel}</form:label>
                    <form:errors path="email" cssStyle="color: #ff0000;"/>
                    <form:input path="email" placeholder="Enter email address" data-theme="d"/>
                    
                    <br></br>
					<!-- ReCaptcha c = ReCaptchaFactory.newReCaptcha("ADD-YOUR-PUBLIC-KEY-HERE", "ADD-YOUR-PRIVATE-KEY-HERE", false); -->   	
					<!--  
						   		<div id="captcha_paragraph">
            						<c:if test="${invalidRecaptcha == true}">
                						<span style="color: #ff0000"><spring:message code="invalid.captcha" text="Invalid captcha. Please try again"/></span>
            						</c:if>
							   		
	            				</div> 
					-->
                   <input type="submit" value="Submit" data-theme="a"/>
		</form:form>
		<a href="login" data-role="button" data-theme="e">Sign in</a>
	</body>
</html>
