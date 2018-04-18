
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	    <title>Login</title>
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.css" />
	    <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	    <script src="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.js"></script>

	</head>
  
	<body>
		<div id="main" data-role="page">
	    	<div data-role="header">
	        	<h1>Login</h1>
	      	</div>
	      	
	      	<div id="content" data-role="content">
		      	<c:if test="${not empty error}">
					<div style="color: #ff0000;">${error}</div>
				</c:if>
	        	<form name='loginForm' action="j_spring_security_check" method='POST' data-ajax="false">
	        		
	            		<label for="j_username">User Name</label>
	            		<input id="j_username" name="j_username" type="text" required placeholder="Enter User Name" data-theme="d"/>
	          	
	          		
	            		<label for="j_password">Password</label>
	            		<input id="j_password" name="j_password" type="password" required placeholder="Enter password" data-theme="d"/>
	          		
	 				<input id="submitid" type="submit" value="Sign in" data-theme="a"/>		
				</form>
				<a href="forgotpassword" data-role="button" data-theme="b">Forgot Password?</a>
				<a href="signup" data-role="button" data-theme="e">Sign Up</a>
	      	</div>
	      	
	      <div data-role="footer">
	        <%@ include file="/resources/jspf/footer.jspf" %>
	      </div>
	    </div>    
	</body>
</html>