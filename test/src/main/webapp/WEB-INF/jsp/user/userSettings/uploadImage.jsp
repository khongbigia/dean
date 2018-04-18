<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html> 
<html> 
	<head> 
		<title>Update Avatar</title> 
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
				$("#UploadButton").hide();
				$("#Upload_Button").click(function() {
					$("#UploadButton").click();
			    });
			});
		</script>
	</head> 
	
	<body> 
		<div data-role="page" id="home" data-theme="d">
			<div data-role="header" data-theme="b">
		            <a href="/test/userSettings/mainpage" data-icon="back" rel="external">Back</a>
				<h1 id="mainpage1">Update Avatar</h1>
		               
			</div>
		
			<div data-role="content">
				<%@ include file="/resources/jspf/notifiedbar.jspf" %>
				<form:form method="POST" commandName="file" action="uploadFile" enctype="multipart/form-data" data-ajax="false">	        		
	        		<input id="UploadButton" type="file" name="file"><br /> 
	        		<form:errors path="file" cssStyle="color: #ff0000;" /><br /> 
	        		Image to upload: <br>
	        		<input id="Upload_Button" type="button" value="Browse Image">
					<input type="submit" value="Upload" data-theme="a"> 
    			</form:form>
			</div>
		</div>
	</body>
</html>

