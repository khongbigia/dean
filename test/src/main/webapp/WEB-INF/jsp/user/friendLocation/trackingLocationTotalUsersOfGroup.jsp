<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Friends</title>
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
			
			table {
			    width: 100%;
			    color: white;			    
			}
	
		</style>	 
		<script type="text/javascript">
            var listOfUsers = null;
            function getListFriendAccepted() {
            $.ajax({
                        type: 'GET',
                        url: "http://localhost:8080/test/user/getListFriendAccepted",
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        processData: false, async: false,
                        success: function (msg) {         
                        	listOfUsers = msg;
                          
                        }
                 });
            }
            
          
            
            function mountUsersToDiv(){
                $.each(listOfUsers, function(key, val) {          	
                	var img = '/test/avatar/'+val;              
                    $("#myTable").find('tbody').append('<tr><td><input type="checkbox" value="'+val+'"/></td><td>'+val+'</td><td><img src='+img+' height="30" width="30"/></td></tr>');
                });
           }
            
            $(document).bind('pageinit', function(){
            	webSocketCommon();
            	var onOff = '<c:out value="${sharelocation}"/>';
                
                if (onOff==="true"){
                    updateLocation();		                   
                }
            	
            	getListFriendAccepted();
                mountUsersToDiv();
                
                $("#showMapButton").bind("click", function(event,ui){
                    var listUsersShowMap = new Array();
                     $(':checked').each(function() {
                     	listUsersShowMap.push($(this).val());
                      });
                 	//alert(listUsersShowMap);
                 	$.session.set('listUsersShowMap', listUsersShowMap);
                    document.location.href="showMap";
                  });

            });
		</script>
	</head> 
	
    <body>
        <div data-role="page">
            <div data-role="header" data-theme="b">
                <a href="/test/mainpage" data-icon="back" rel="external">Back</a>
				<h1>Friends</h1>
			</div>

			<div data-role="content">
				<%@ include file="/resources/jspf/notifiedbar.jspf" %>
				<table data-role="table" data-mode="columntoggle" class="ui-responsive ui-shadow" id="myTable">
					<tbody>
					
					</tbody>
	           </table> 
	           <a href="#" data-role="button" id="showMapButton" data-theme="b">Show Map</a>
			</div>
      </div>  

    </body>
</html>
