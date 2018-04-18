<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html> 
<html> 
	<head> 
		<title>Main Page</title> 
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
			.icon-label { color: blue; display: block; font-size:14px; }
			a:link,	a:visited, a:hover, a:active { text-decoration:none; }
			
			#avata_status{
				margin-left: auto;
    			margin-right: auto;
    			width: 100px;
    			text-align:center;
    			color: #00ff00;
			}
			.bar {   
				    width: auto;
				    height: 40px;
				    margin: -10px;
				    border-width: 1px;
				    border-style: solid;
				    border-color: rgba(0,0,0,.2);
				    background-color: #66CCCC;
				}

			#alert {   
			    width: 30;
			    height: 30px;
			    margin: 5px;
			    border-width: 0px;
			    background-color:white;
			}
			
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
		<div data-role="page" id="home" data-theme="d">
			<div data-role="header" data-theme="b">		          
				<h1 id="mainpage">Main</h1>		               
			</div>
			<div data-role="content">
				<%@ include file="/resources/jspf/notifiedbar.jspf" %>
				<div class="ui-grid-a"> 
					<div class="ui-block-a">
				   		<a href=<c:url value="j_spring_security_logout"/> id="logoutButton" data-ajax="false">
					  		<img src="/test/resources/images/logout-icon.JPG" height="57" width="57">
					  		<span class="icon-label">Logout</span>
		                </a>
					</div>
		                    
					<div class="ui-block-b">
					   	<a href="/test/userSettings/mainpage" id="UserSettings" data-transition="slide" rel="external">
						  	<img src="/test/resources/images/userSetting-icon1.JPG" height="57" width="57">
						  	<span class="icon-label">User Settings</span>
			            </a>
					</div>
		                    
					<div class="ui-block-a">
				   		<a href="user/trackingLocationTotalUsersOfGroup" rel="external">
					  		<img src="/test/resources/images/friendsLocation.jpg" height="57" width="57">
					  		<span class="icon-label">Friends' Locations</span>
		                </a>
					</div>
						
					<div class="ui-block-b">
				   		<a href="user/chatMainPage" data-transition="slide" rel="external">
					  		<img src="/test/resources/images/chat-icon2.JPG" height="57" width="57">
					  		<span class="icon-label">Chat</span>
		                </a>
					</div>	

		            <div class="ui-block-a">
				   		<a href="user/makefriend" rel="external" >
					  		<img src="/test/resources/images/makefriend-icon1.JPG" height="57" width="57">
					  		<span class="icon-label">Make Friends</span>
		                </a>
		            </div>
		                    
		            <div class="ui-block-b">
				   		<a href="more/moreMainPage.html" data-transition="slide">
					  		<img src="/test/resources/images/more-icon.JPG" height="57" width="57">
					  		<span class="icon-label">More (coming soon)</span>
		                </a>
		            </div>		                 		                     
				</div>
			</div>
		</div>
	</body>
</html>

