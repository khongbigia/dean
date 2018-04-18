<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html> 
	<head>
		<title>New Password Done</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.css" />
		<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.js"></script>      
		<script type="text/javascript">
			$(document).ready(function(){
				document.location.href="#NewPasswordDone";
				
			});
		</script>
	</head>
	<body>
		<div data-role="dialog" id="NewPasswordDone">
            <div data-role="header">
				<h1>Good Job</h1>
            </div>
	
            <div data-role="content" data-theme="b">	
				Your new password is set.

				<a href="http://localhost:8080/test/userSettings/mainpage" data-role="button" data-theme="c" data-ajax="false">Ok</a>
            </div>
        </div>

	</body>
</html>
