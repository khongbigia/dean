<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Make Friend Confirmation</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.css" />
		<link rel="stylesheet" type="text/css" href="/test/resources/css/generic.css" />
		<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.js"></script>      
		<script type="text/javascript">
			
			function makefriendrequest(friendname) {
	            $.ajax({
	                        type: 'POST',
	                        url: "http://localhost:8080/test/user/makefriendrequest" + '/' + friendname ,
	                        contentType: "application/json; charset=utf-8",
	    	                dataType: "json",
	    	                processData: false, async: false,
	                        success: function (result) {	                        	
	                            //document.location.href="makeFriends.html";
	                        }
	                 });
	            }

	      	$(document).bind('pageinit', function(){   
	      		
	      		var target="ws://localhost:8080/test/websocket";
	      		websocket = new WebSocket(target);
	      		websocket.onopen = function (evt) {
                    //alert("CONNECTED");
	      		}
	      		
	      		
	      		var friendname = '<c:out value="${friendname}"/>';
	           	$("#YesButton").bind("click", function(event,ui){
					makefriendrequest(friendname);
					
					var msg = {};
					msg.type = 0; //0 --> friend req notification.(from c to s)
					msg.sender = "";
					msg.receiver = [];
					msg.receiver[0] = friendname;
					msg.content = "";
                	var msg_json = JSON.stringify(msg);           
                	websocket.send(msg_json);             	
					document.location.href="makefriend";	
	            });
	                
				$("#NoButton").bind("click", function(event,ui){
	            	document.location.href="makefriend";
			});
	
	            });
		</script>
	</head> 
	
    <body>
        <div data-role="dialog" id="MakeFriendRequest">
            <div data-role="header">
				<h1>Yes/No</h1>
            </div>
	
            <div data-role="content" data-theme="b">	
				Do you want to make friend with this person?

				<a href="#" id="YesButton" data-role="button" data-theme="c">Yes</a>
                <a href="#" id="NoButton" data-role="button" data-theme="c">No</a>
            </div>
        </div>
     </body>
</html>
