<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message var="pageTitle" code="success_recover_password.pageTitle" />


<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><c:out value="${pageTitle}" /></title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
	    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.css" />
	    <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	    <script src="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.js"></script>
	    
		<script>
			$(document).ready(function(){
				document.location.href="#recover_password";
			});
		</script>
	</head>
	<body>
		<div data-role="dialog" id="recover_password">
            <div data-role="header">
				<h1>Next step</h1>
            </div>
	
            <div data-role="content" data-theme="b">	
				Please, check your email and click the link inside email to reset password.

				<a href="http://localhost:8080/test/login" data-role="button" data-theme="c">Sign in</a>
            </div>
        </div>

	</body>
</html>
