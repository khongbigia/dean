<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
		<title>New Password</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
	    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.css" />
	    <link rel="stylesheet" type="text/css" href="/test/resources/css/generic.css" />
	    <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	    <script src="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.js"></script>
	    <script src="/test/resources/js/generic.js"></script>
	    <style>
            .ui-page{
                background: transparent url(/test/resources/images/BG.PNG) repeat center center;
            }

			/* center icons */
			.ui-grid-a { text-align: center; }
			
			/* set row height */
			.ui-block-a, .ui-block-b { height: 100px; }
			
			/* set label color, size */
			.icon-label { color: #6495ed; display: block; font-size:14px; }
			a:link,	a:visited, a:hover, a:active { text-decoration:none; }		
		</style>
		<script>
			$(document).ready(function() {
				webSocketCommon();
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
 	<div data-role="page" id="home" data-theme="d">
			<div data-role="header" data-theme="b">
				<a href="/test/userSettings/mainpage" data-icon="back" rel="external">Back</a>
	        	<h1>New Password</h1>
	      	</div>
	      	
	      	<div id="content" data-role="content">
	      		<%@ include file="/resources/jspf/notifiedbar.jspf" %>
		      	<form name='newPasswordForm' action="newpasswordSubmit" method='post' data-ajax="false">
		      		<p style="color: #ff0000;" id="validate-status"></p>
 					New password
					<input id="password" name="password" type="password"  placeholder="Enter new password" data-theme="d">
					Re-type new password
					<input id="confirmedpassword" name="confirmedpassword" type="password"  placeholder="Enter new password" data-theme="d">
					<input type="hidden" name="username" value=<c:out value="${username}"/> />
					<input id="submit" name="submit" type="submit" value="Submit" data-theme="a" />

				</form>
	      	</div>
	     
	 </div>   
</body>
</html>
