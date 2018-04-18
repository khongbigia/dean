var friendNotifyonoff = 0;
var messageNotify = 0;
function blink(selector){
	$(selector).fadeOut('slow', function(){
		$(this).fadeIn('slow', function(){
    	    blink(this);
    	});
    });
}  

function off(selector){
    $(selector).remove();
}

function webSocketCommon(){
	var websocket;
	var target="ws://localhost:8080/test/websocket";
	websocket = new WebSocket(target);
	websocket.onopen = function (evt) {
        //alert("CONNECTED");
	};
	websocket.onmessage = function (evt) {
        var msg_json = JSON.parse(evt.data);
        if (typeof offResIcon === 'undefined'){
	        if (msg_json.type===1) {
	        	if (friendNotifyonoff===0){
		        	$( "#alert" ).append( '<a rel ="external" href="http://localhost:8080/test/user/listFriendsWaitingAccept"> <img id="friendNotify" class="notify" src="/test/resources/images/red-ask.PNG" alt="Res" width="30" height="26"> </a>' );
		        	blink("#friendNotify"); 
		        	friendNotifyonoff=1;
		        	//alert(friendNotifyonoff);
	        	}
	        }
        }
        if (typeof offMesIcon === 'undefined'){
	        if (msg_json.type===3) {
	        	if (messageNotify===0){
		        	$( "#alert" ).append( '<a rel ="external" href="http://localhost:8080/test/user/messageBox"> <img id="messageNotify" class="notify" src="/test/resources/images/red-env.PNG"" alt="Mes" width="30" height="26"> </a>' );
		        	blink("#messageNotify");   
		        	messageNotify=1;
	        	}
	        }
        }
    };
}