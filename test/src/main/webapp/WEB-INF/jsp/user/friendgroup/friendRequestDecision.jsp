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
			
            function updateFriendRequestToAccept(id) {
	            $.ajax({
	                        type: 'PUT',
	                        url: "http://localhost:8080/test/user/updateFriendRequestToAccept" + '/' + id,
	                        contentType: "application/json; charset=utf-8",
	    	                dataType: "json",
	    	                processData: false, async: false,
	                        success: function (result) {
	                        }
	                 });
	            }
            
            function denyFriendRequest(id) {
                $.ajax({
                            type: 'DELETE',
                            url: "http://localhost:8080/test/user/denyFriendRequest" + '/' + id,
                            contentType: "application/json; charset=utf-8",
        	                dataType: "json",
        	                processData: false, async: false,
                            success: function (result) {
                            }
                     });
                }
            
            
            $(document).bind('pageinit', function(){
            	var id = '<c:out value="${id}"/>'
				$("#OkButton").bind("click", function(event,ui){ 
					updateFriendRequestToAccept(id);
					document.location.href="listFriendsWaitingAccept";
                 });
				
             	$("#DenyButton").bind("click", function(event,ui){
             		denyFriendRequest(id)
             		document.location.href="listFriendsWaitingAccept";
                 });
                
               	$("#NoDecisionButton").bind("click", function(event,ui){
                    document.location.href="listFriendsWaitingAccept";
				});

            });
	</script>
	</head> 
    <body>
        
        
        <div data-role="dialog">
            <div data-role="header">
				<h1>Friend Decision</h1>
            </div>
	
            <div data-role="content" data-theme="b">	
				<a href="#" id="OkButton" data-role="button" data-theme="c">Allow</a>
                <a href="#" id="DenyButton" data-role="button" data-theme="c">Deny</a>
                <a href="#" id="NoDecisionButton" data-role="button" data-theme="c">Decide Later</a>
            </div>         
		</div>
     </body>
     
</html>
