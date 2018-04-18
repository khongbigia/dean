<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	    <title>Recover Password</title>
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.css" />
	    <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	    <script src="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.js"></script>

	</head>
  
	<body>
		<div id="main" data-role="page">
	    	<div data-role="header">
	        	<h1>Recover Password</h1>
	      	</div>
	      	
	      	<div id="content" data-role="content">
		      	<form name='RecoverPasswordForm' action="submitRecoverPassword" method='post' data-ajax="false">
 					Enter user name or email
					<input name="username" type="text"  placeholder="user name or email">
					<input name="submit" type="submit" value="Submit" />

				</form>
				<a href="login" data-role="button" data-theme="e">Sign in</a>
	      	</div>
	      	
	      <div data-role="footer">
	        <%@ include file="/resources/jspf/footer.jspf" %>
	      </div>
	    </div>    
	</body>
</html>