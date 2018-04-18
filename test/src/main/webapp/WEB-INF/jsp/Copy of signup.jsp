<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>


<c:url var="submitRegistrationUrl" value="/signup" />
<spring:message var="pageTitle" code="newUserRegistration.pageTitle" />
<spring:message var="userNameLabel" code="newUserRegistration.label.username" />
<spring:message var="passwordLabel" code="newUserRegistration.label.password" />
<spring:message var="register" code="newUserRegistration.label.register" />
<spring:message var="lastNameLabel" code="newUserRegistration.label.lastname" />
<spring:message var="firstNameLabel" code="newUserRegistration.label.firstname" />
<spring:message var="emailLabel" code="newUserRegistration.label.email" />

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><c:out value="${pageTitle}" /></title>
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
		
		<form:form action="${submitRegistrationUrl}" modelAttribute="users">
                    <p><spring:message code="newUserRegistration.message.allFieldsRequired" /></p>
                    <table>
                        <tr>
                            <td><form:label path="userName">${userNameLabel}</form:label></td>
                            <td><form:input path="userName"  placeholder="Enter User Name" /></td>
                            <td><form:errors path="userName" cssStyle="color: #ff0000;"/></td>
                            
                        </tr>
                        
                        <tr>
                            <td><form:label path="password">${passwordLabel}</form:label></td>
                            <td><form:password path="password" placeholder="Enter Password" /></td>
                            <td><form:errors path="password" cssStyle="color: #ff0000;"/></td>
                        </tr>
                        
                        <tr>
                            <td><form:label path="confirmedPassword">confirmPassword</form:label></td>
                            <td><form:password path="confirmedPassword" placeholder="Enter Password" /></td>
                            <td><form:errors path="confirmedPassword" cssStyle="color: #ff0000;"/></td>
                        </tr>
                        
                        <tr>
                            <td><form:label path="lastName">${lastNameLabel}</form:label></td>
                            <td><form:input path="lastName" placeholder="Enter Last name" /></td>
                            <td><form:errors path="lastName" cssStyle="color: #ff0000;"/></td>
                        </tr>
                        
                        <tr>
                            <td><form:label path="firstName">${firstNameLabel}</form:label></td>
                            <td><form:input path="firstName" placeholder="Enter First name"/></td>
                            <td><form:errors path="firstName" cssStyle="color: #ff0000;"/></td>
                        </tr>
                        
                        <tr>
                            <td><form:label path="email">${emailLabel}</form:label></td>
                            <td><form:input path="email" placeholder="Enter Email"/></td>
                            <td><form:errors path="email" cssStyle="color: #ff0000;"/></td>
                        </tr>
                        
                        <tr>
                        	<!-- ReCaptcha c = ReCaptchaFactory.newReCaptcha("ADD-YOUR-PUBLIC-KEY-HERE", "ADD-YOUR-PRIVATE-KEY-HERE", false); -->   	
						   <td>
						   		<div id="captcha_paragraph">
            						<c:if test="${invalidRecaptcha == true}">
                						<span style="color: #ff0000"><spring:message code="invalid.captcha" text="Invalid captcha please try again"/></span>
            						</c:if>
							   		<%
	                					ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LfflgETAAAAAOkQbE57U1FIvueaC7g9ScVpDqf9","6LfflgETAAAAACaia3iVhhwqZZLgl4ZtRmUuPIkY", false);
	                					out.print(c.createRecaptchaHtml(null, null));
	            					%> 
	            				</div> 
            				</td>    
						</tr>
                        </tr>
                            <td colspan="2">
                                <input type="submit" value="Submit"/>
                            </td>
                        </tr>
                    </table>
		
		</form:form>
		
		<div id="main" data-role="page">
	    	<div data-role="header">
	        	<h1>Sign up</h1>
	      	</div>
	      	
	      	<div id="content" data-role="content">
	      		<div style="color: #ff0000;">
					<h2><c:out value="${duplicateIdEmail}" /></h2>
				</div>
				
	        	<form:form action="${submitRegistrationUrl}" modelAttribute="users">
                    <p><spring:message code="newUserRegistration.message.allFieldsRequired" /></p>
                    
                    <form:label path="userName">${userNameLabel}</form:label>
                    <form:input path="userName"  placeholder="Enter user name" />
                    <form:errors path="userName" cssStyle="color: #ff0000;" />
                    
                    <input n="userName"  placeholder="Enter user name" />
                    <form:errors path="userName" cssStyle="color: #ff0000;" />
                            
					<form:label path="password">${passwordLabel}</form:label>
                    <form:password path="password" placeholder="Enter password" />
                    <form:errors path="password" cssStyle="color: #ff0000;"/>

                    <form:label path="confirmedPassword">Confirm Password</form:label>
                    <form:password path="confirmedPassword" placeholder="Enter confirm password" />
                    <form:errors path="confirmedPassword" cssStyle="color: #ff0000;"/>

					<form:label path="lastName">${lastNameLabel}</form:label>
                    <form:input path="lastName" placeholder="Enter last name" />
                    <form:errors path="lastName" cssStyle="color: #ff0000;"/>

					<form:label path="firstName">${firstNameLabel}</form:label>
                    <form:input path="firstName" placeholder="Enter first name"/>
                    <form:errors path="firstName" cssStyle="color: #ff0000;"/>
                    
                    <form:label path="email">${emailLabel}</form:label>
                    <form:input path="email" placeholder="Enter email address"/>
                    <form:errors path="email" cssStyle="color: #ff0000;"/>

					<!-- ReCaptcha c = ReCaptchaFactory.newReCaptcha("ADD-YOUR-PUBLIC-KEY-HERE", "ADD-YOUR-PRIVATE-KEY-HERE", false); -->   	

						   		<div id="captcha_paragraph">
            						<c:if test="${invalidRecaptcha == true}">
                						<span style="color: #ff0000"><spring:message code="invalid.captcha" text="Invalid captcha please try again"/></span>
            						</c:if>
							   		<%
	                					ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LfflgETAAAAAOkQbE57U1FIvueaC7g9ScVpDqf9","6LfflgETAAAAACaia3iVhhwqZZLgl4ZtRmUuPIkY", false);
	                					out.print(c.createRecaptchaHtml(null, null));
	            					%> 
	            				</div> 

                   <input type="submit" value="Submit"/>
			</form:form>
				<a href="login" data-role="button" data-theme="e">Sign in</a>
	      	</div>
	      	
	      <div data-role="footer">
	        <%@ include file="/resources/jspf/footer.jspf" %>
	      </div>
	    </div>    
	</body>
</html>
