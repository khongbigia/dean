        var timer1;
        var lat ;
        var lng ;
        function updateLocation(){
        	if ( navigator.geolocation ) {
                function successUpdate(pos) {
                	
                    lat = $.session.get('latitude');    
                    lng = $.session.get('longitude');
                    //if(typeof variable_here === 'undefined')
                    if (! lat){
                    	lat=1000;
                    	lng=1000;
                    }
                    editUserLocation(pos.coords.latitude, pos.coords.longitude);
                    //alert(lat+" "+lng);
                    if ((Math.abs(pos.coords.latitude-lat)>0.00001) && (Math.abs(pos.coords.longitude-lng)>0.00001)){
                    		editUserLocation(pos.coords.latitude, pos.coords.longitude);
                    		$.session.set('latitude', pos.coords.latitude);
                            $.session.set('longitude', pos.coords.longitude);
                            
                    }
                }
                
                function fail(error) {
                  alert("No Location Support");
                }
                timer1 = setInterval(function(){navigator.geolocation.getCurrentPosition(successUpdate, fail, {maximumAge: 500, enableHighAccuracy:true, timeout: 6000});},10000);
            } 
            else {
                alert("Not support Location");
                alert("not1");
                clearInterval(timer1);
            }
        }
        
  
        function editUserLocation(latTmp, lngTmp) {
            $.ajax({
                        type: 'PUT',
                        url: "http://localhost:8080/test/user/updatelatlong" + '/' + latTmp + '/' + lngTmp +'/',
                        success: function (result) {
                            alert("ok");
                        },
                        error: function (error) {
                            alert("fail");
                        }
                 });
            }
        