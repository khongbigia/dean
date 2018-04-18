<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Make Friend</title>
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

			/* set row height */
			#listUsersListView li {height: 54px;}
	
		</style>	     
		   
		<script type="text/javascript">
	    	var listUsers = null;
	    	var offResIcon="true";
	    	var username = '<c:out value="${username}"/>'
	            function listFriendsWaitingAccept() {
		            $.ajax({
		            	type: 'GET',
		                url: "http://localhost:8080/test/user/getListFriendsWaitingAccept",
		                        contentType: "application/json; charset=utf-8",
		                        dataType: "json",
		                        processData: false, async: false,
		                        success: function (result) {
									listUsers = result;
		                          
		                        }
		             });
	            }
	            
	            function mountlistUsers(){
	                    $.each(listUsers, function(key, val) {
	                    	var img = '/test/avatar/'+val;
	                        $( "#listUsersListView" ).append('<li><a href="#">'+'<img src='+img+' height="50" width="50">'+'<h2>'+val+'</h2>'+'</a>'+'<a href=' + "friendRequestDecision?id=" + key + ' rel="external" data-transition="pop" data-icon="arrow-r"></a>'+'</li>').listview("refresh");
	                   
	                    });
	            }
	                
	            $(document).bind('pageinit', function(){
	            	webSocketCommon();
	            	
	            	var onOff = '<c:out value="${sharelocation}"/>';		                
	                if (onOff==="true"){
	                    updateLocation();		                   
	                }
	                
	            	listFriendsWaitingAccept();
	                mountlistUsers();
	            });
		</script>
	</head> 
	
    <body>
		<div data-role="page" id="YourOwnGroups">
			<div data-role="header" data-theme="b">
				 <a href="/test/mainpage" data-icon="back" rel="external">Back</a>
				<h1>Requests</h1>
			</div>
                
			<div data-role="content">
				<%@ include file="/resources/jspf/notifiedbar.jspf" %>
	           <ul data-role="listview" id="listUsersListView" data-autodividers="true" data-filter="true" data-filter-placeholder="Search Friends...">
	           		
	           </ul>
        	</div>
		</div>  
	</body>
	
</html>

