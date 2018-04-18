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
		<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
        <style>
			#map-page, #map-canvas { width: 100%; height: 100%; padding: 0; }
		</style>
        <script type="text/javascript">
            var currentSum=0;
            var Sum=0;
            var map;
            var detect=0;
            var latlngGlobal;
            var zoomGlobal;
            
            var ListUserEntity = null;
            function getListUserEntity(listUseridString) {
            $.ajax({
                        type: 'GET',
                        url: "http://localhost:8080/test/user/getListUserEntityFromListUserName" + '/' + listUseridString ,
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        processData: false, async: false,
                        success: function (msg) {
                            //alert("ok");
                            ListUserEntity = msg;
                            //alert(ListUserEntity[0].userName);
                            
                          
                        }
                 });
            }
            
            var markerArray = new Array();
            
            function markerCreate(name, lat, lng, index){
                var latLngTmp = new google.maps.LatLng(lat, lng);
                
                markerArray[index] = new google.maps.Marker({
                    position: latLngTmp,
                    draggable: false,
                    zIndex: 1,
                    map: map,
                    optimized: false,
                    icon: "http://localhost:8080/test/resources/markers/" + name + ".png"
                });
            }
            
            function mapShow() {
            	var latLng = new google.maps.LatLng(ListUserEntity[0].latitude, ListUserEntity[0].longitude);
                
                if (detect===0)
                {
                	latlngGlobal=latLng;
					map = new google.maps.Map(document.getElementById('map-canvas'), {
	                    zoom: 11,
	                    center: latlngGlobal,
	                    mapTypeId: google.maps.MapTypeId.ROADMAP
                    });
					detect=detect+1;
                }
            	else{
                	latlngGlobal=latLng;
                	zoomGlobal=map.getZoom();

	                map = new google.maps.Map(document.getElementById('map-canvas'), {
	                    zoom: 11,
	                    center: latlngGlobal,
	                    mapTypeId: google.maps.MapTypeId.ROADMAP
	                 });
					map.setZoom(zoomGlobal);
            	}
             
                
				$.each(ListUserEntity, function(key, val) {
					markerCreate(val.userName, val.latitude, val.longitude, key);
				});
			}
          
            $(document).ready(function(){
                var listUsersShowMapString =  $.session.get('listUsersShowMap');        
                getListUserEntity(listUsersShowMapString);
                mapShow();
                timer = setInterval(function(){getListUserEntity(listUsersShowMapString);mapShow();}, 20000);
                
            });
	</script>
	</head> 
    <body> 
    	<div data-role="page" id="map-page">
            <div data-role="header" data-theme="b">
                <a href="trackingLocationTotalUsersOfGroup" data-icon="back" rel="external">Back</a>
                <h1>Location Users</h1>
			</div>

			<div data-role="content" id="map-canvas">
			</div> 
      	</div>  
    </body>
</html>
