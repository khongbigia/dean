<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html> 
<html> 
	<head> 
		<title>Setting</title> 
		<meta name="viewport" content="width=device-width, initial-scale=1">
	   	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.css" />
	    <link rel="stylesheet" type="text/css" href="/test/resources/css/generic.css" />
	    <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	    <script src="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.js"></script>
	    <script src="/test/resources/js/generic.js"></script>
	    <script src="/test/resources/js/jquery.session.js"></script>
	    <script src="/test/resources/js/updateLocation.js"></script>
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
			$(document).bind('pageinit', function(){
		            	webSocketCommon();
		            	
		            	var onOff = '<c:out value="${sharelocation}"/>';		                
		                if (onOff==="true"){
		                    updateLocation();		                   
		                }
		    });
		</script>
	</head> 
	
	<body> 	
		<div data-role="page" data-theme="d">
			<div data-role="header" data-theme="b">
		        <a href="/test/mainpage" data-icon="back" rel="external">Back</a>
				<h1>Chat Main</h1>		              
			</div>
		
			<div data-role="content">
				<%@ include file="/resources/jspf/notifiedbar.jspf" %>
				<div class="ui-grid-a"> 
					<div class="ui-block-a">
				   		<a href="chatOneOneList" rel="external">
					  		<img src="/test/resources/images/chat-one-one-icon.JPG" height="57" width="57">
					  		<span class="icon-label">Chat One To One</span>
		                </a>
					</div>
		                    
					<div class="ui-block-b">
				   		<a href="#" rel="external">
					  		<img src="/test/resources/images/voiceChat-icon.JPG" height="57" width="57">
					  		<span class="icon-label">Voice Chat</span>
		                </a>
					</div>       
				</div>
			</div>
		</div>
	</body>
</html>

