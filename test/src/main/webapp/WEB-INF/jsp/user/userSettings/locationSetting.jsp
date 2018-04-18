<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html> 
<html> 
	<head> 
		<title>Location Setting</title> 
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
		</style>
		<script type="text/javascript">
			function editShareLocation(stat) {
            $.ajax({
                        type: 'PUT',
                        url: "http://localhost:8080/test/user/updatesharelocationstatus" + '/' + stat,
                        contentType: "application/json; charset=utf-8",
    	                dataType: "json",
    	                processData: false, async: false,
                        success: function (result) {
                            //alert("ok s");
                        },
                        error: function (error) {
                            //alert("fail s");
                        }
                 });
            }
			$(document).bind('pageinit', function(){
				webSocketCommon();
          		var username = '<c:out value="${username}"/>';         		
                var onOff = '<c:out value="${sharelocation}"/>';
                
                if (onOff==="true"){
                    updateLocation();
                    $('#On').button('disable');
                }
                else{
                	$('#Off').button('disable');
                }

                $("#Off").bind("click", function(event,ui){
                    $('#Off').button('disable');
                    $('#On').button('enable');
                    //$.session.set('broadcast', "off");
                    onOff="false";
                    editShareLocation("false");
                    $('#broadcast').html("Share Location :"+onOff);
                    clearInterval(timer1);
                });
                
                $("#On").bind("click", function(event,ui){
                  
                   if ( navigator.geolocation ) {
                        $('#On').button('disable');
                        $('#Off').button('enable');
                        onOff="true";
                        editShareLocation("true");
                        $('#broadcast').html("Share Location :"+onOff);               
                        updateLocation();} 
                    else {
                        alert("Not support Location");
                        clearInterval(timer1);
                    }

                    
                });
         });
	</script>
	</head> 
	
	<body> 
		<div data-role="page" id="LocationSetting">
			<div data-role="header" data-theme="b">
				 <a href="/test/userSettings/mainpage" data-icon="back" rel="external">Back</a>
				<h1>Location Setting</h1>
			</div>
			
			<div data-role="content" id="map-canvas">
			<%@ include file="/resources/jspf/notifiedbar.jspf" %>
				<div id="status"></div>
				<input type="button" value="Turn Off" id="Off" />
		        <input type="button" value="Turn On" id="On" />
			</div>	
		</div>
	
	</body>
</html>