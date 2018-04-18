 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>New Password</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
	    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.css" />
	    <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	    <script src="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.js"></script>
		<script>
			$(document).ready(function() {
				$('[type="submit"]').button('disable'); 
				$("#confirmedpassword").keyup(validate);
				$("#password").keyup(validate);
			});
			
			function validate() {
				
				var password = $("#password").val();				
				var confirmedpassword = $("#confirmedpassword").val();
				if (($("#password").val().length>7) && ($("#password").val().length<46)){
					if (password == confirmedpassword) {
						$("#validate-status").text("");   
						$('[type="submit"]').button('enable');
					}
					else{
						$("#validate-status").text("password not matched");  
						//$("#submit").attr("disabled","disabled");
						$('[type="submit"]').button('disable');
					}
				}
				else{
					$('[type="submit"]').button('disable');
					$("#validate-status").text("Password: 8 - 45 characters");
				}
			}
		</script>
</head>
<body>
 	<div id="main" data-role="page">
	    	<div data-role="header">
	        	<h1>New Password</h1>
	      	</div>
	      	
	      	<div id="content" data-role="content">
		      	<form name='newPasswordForm' action="newpasswordSubmit" method='post' data-ajax="false">
		      		<p style="color: #ff0000;" id="validate-status"></p>
 					New password
					<input id="password" name="password" type="password"  placeholder="Enter new password" data-theme="d">
					Re-type new password
					<input id="confirmedpassword" name="confirmedpassword" type="password"  placeholder="Enter new password" data-theme="d">
					<input type="hidden" name="username" value=${username} />
					<input id="submit" name="submit" type="submit" value="Submit" data-theme="a" />

				</form>
				<a href="login" data-role="button" data-theme="e">Sign in</a>
	      	</div>
	      	
	      <div data-role="footer">
	        <%@ include file="/resources/jspf/footer.jspf" %>
	      </div>
	 </div>   
</body>
</html>
