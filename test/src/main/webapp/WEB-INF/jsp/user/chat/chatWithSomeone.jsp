<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Chat 1 on 1</title>
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
		                background: transparent url(/test/resources//images/chat-background-icon2.jpg) repeat center center;
		            }
	            
		     .senderstyle {
				  	word-wrap: break-word;
				    position: relative;
				    width: 250px;
				    font-size: 120%;
					padding: 0px;
					text-align:center;
				    background: #e3eb00;
				    -webkit-border-radius: 5px;
				    -moz-border-radius: 5px;
				    border-radius: 5px;
				    -webkit-box-shadow: 2px 2px 10px 0px #616161;
				    -moz-box-shadow: 2px 2px 10px 0px #616161;
				    box-shadow: 2px 2px 10px 0px #616161;
				}
				
				.senderstyle:after {
				    content: "";
				    position: absolute;
				    top: -9px;
				    left: 7px;
				    border-style: solid;
				    border-width: 0 7px 9px;
				    border-color: #e3eb00 transparent;
				    display: block;
				    width: 0;
				    z-index: 1;
				}
		   
		   .receiverstyle {
		   		word-wrap: break-word;
			    position: relative;
			    width: 250px;
			    font-size: 120%;
			    color: #FFFFFF;
								padding: 0px;
								text-align:center;
			    background: #3F61F5;
			    -webkit-border-radius: 5px;
			    -moz-border-radius: 5px;
			    border-radius: 5px;
			    -webkit-box-shadow: 2px 2px 10px 0px #616161;
			    -moz-box-shadow: 2px 2px 10px 0px #616161;
			    box-shadow: 2px 2px 10px 0px #616161;
			}

		  .receiverstyle:after {
			    content: "";
			    position: absolute;
			    top: -9px;
			    left: 228px;
			    border-style: solid;
			    border-width: 0 7px 9px;
			    border-color: #3F61F5 transparent;
			    display: block;
			    width: 0;
			    z-index: 1;
			}
			
			.space5 {
			    position: relative;
			    height : 5px;
			    }
			
			.space60 {
			    position: relative;
			    height : 60px;
			    }
			    
	    </style>	
        
		<script type="text/javascript">
	        var websocket;   
			var allUnseenMessages=null;
			var oldMessages=null;
			var oneTime = 0;
			var datetp=null;
	        function getAllUnseenMessages(sender) {
	            $.ajax({
	            	type: 'GET',
	                url: "http://localhost:8080/test/user/getUnseenMessages" + '/' + sender,
	                contentType: "application/json; charset=utf-8",
	                dataType: "json",
	                processData: false, async: false,
	                success: function (result) {
						allUnseenMessages=result;
						
	                }
	            });
	        } 
	        
	        function getSomeOldMessages(sender, d) {
	            $.ajax({
	            	type: 'GET',
	                url: "http://localhost:8080/test/user/getSomeOldMessages" + '/' + sender + '/' + d,
	                contentType: "application/json; charset=utf-8",
	                dataType: "json",
	                processData: false, async: false,
	                success: function (result) {
	                	oldMessages=result;
	                	//alert(oldMessages);
						
	                }
	            });
	        } 
	            
	            function mountMessages(){
	                $.each(allUnseenMessages, function(key, val) {
	                	var d = new Date(val.dateCreated);
	                	//alert(val.dateCreated);
	                	$("#showContent").append('<img src=<c:out value="${avatarreceiverpath}"/> align="right" height="50" width="50">');
	                	$("#showContent").append('<div class="space60"></div>');
	                    $("#showContent").append('<div align="right"><div class="receiverstyle"><div class="space5"></div>'+ val.content + "<br>" + d.toLocaleTimeString() + "  " + d.toLocaleDateString()+'<div class="space5"></div></div></div><br>');
	                    if (oneTime===0){
	                    	oneTime = 1;
	                    	datetp = val.dateCreated;
	                    }
	                    
	
	                });
	            }
	            
	            function mountOldMessages(me){
	                $.each(oldMessages, function(key, val) {
	                	var d = new Date(val.dateCreated);
	                	datetp = val.dateCreated;
	                	if (val.sender===me){
	                		$("#showContent").prepend('<img src=<c:out value="${avatarpath}"/> height="50" width="50"><div class="space5"></div><div class="senderstyle"><div class="space5"></div>'+ val.content + "<br>" + d.toLocaleTimeString() + "  " + d.toLocaleDateString() + '<div class="space5"></div></div><br>');
		               
	                	}
	                	else{
		                	$("#showContent").prepend('<img src=<c:out value="${avatarreceiverpath}"/> align="right" height="50" width="50"><div class="space60"></div><div align="right"><div class="receiverstyle"><div class="space5"></div>'+ val.content + "<br>" + d.toLocaleTimeString() + "  " + d.toLocaleDateString()+'<div class="space5"></div></div></div><br>');	         
	                	}
	
	                });
	            }
	      
	      
	      $(document).ready( function(){
	    	  
		    	var onOff = '<c:out value="${sharelocation}"/>';		                
	            if (onOff==="true"){
	            	updateLocation();		                   
	            }	
	    	  
				var me = '<c:out value="${username}"/>';
				var you = '<c:out value="${receiver}"/>';
				var caller = '<c:out value="${caller}"/>';
				$("#mainpageButton").bind("click", function(event,ui){                  
	                  if (caller==="chatOneOneList")
	                    document.location.href="chatOneOneList";
	                else
	                    document.location.href="messageBox";
				});
				$("#mainpageButton1").bind("click", function(event,ui){                  
	                  if (caller==="chatOneOneList")
	                    document.location.href="chatOneOneList";
	                else
	                    document.location.href="messageBox";
				});
				
				var d = new Date();
				datetp = d.getTime();
	            getAllUnseenMessages(you);
	            mountMessages();
	            getSomeOldMessages(you, datetp);
	            mountOldMessages(me);
	            $("html, body").animate({ scrollTop: $(document).height() }, "slow");
	            //$(document).scrollTop(100);
	            $(document).scroll(function() {
	    	  		if ($(document).scrollTop()===0){
	    	  			getSomeOldMessages(you, datetp);
	    	            mountOldMessages(me);
	    	            $(document).scrollTop(5);
	    			}
	    	  });
	            
	            var target="ws://localhost:8080/test/websocket";
	      		websocket = new WebSocket(target);
	      		websocket.onopen = function (evt) {
                    //alert("CONNECTED");
	      		};
	      		
	      		websocket.onmessage = function (evt) {   	         
	   	        	var msg = JSON.parse(evt.data);
	   	        	//alert(msg);
	   	         	if (msg.type===3)
	   	         	{
		   	        	 getAllUnseenMessages(you);
		   		         mountMessages();
	   	         	}
	   	        };
	      		
	            $("#sendButton").bind("click", function(event,ui){
					var content = $("#mesg").val()+"";      
	               	content=jQuery.trim(content);
	               	if (content.length>0){
	                	var chatMsg = {};
	                	chatMsg.type = 2;
	                	chatMsg.sender = me;
	                	chatMsg.receiver = []
	                	chatMsg.receiver[0] = you;
	                	chatMsg.content = content;
	                
	                	var jsonstr = JSON.stringify(chatMsg);
	                	//alert(jsonstr);
	                	websocket.send(jsonstr);
	                	var ti = new Date();
	                	$("#showContent").append('<img src=<c:out value="${avatarpath}"/> height="50" width="50">');
	                	$("#showContent").append('<div class="space5"></div>');
	                	$("#showContent").append('<div class="senderstyle"><div class="space5"></div>'+ content + "<br>" + ti.toLocaleTimeString() + "  " + ti.toLocaleDateString() + '<div class="space5"></div></div><br>');
	               
	                    $("#mesg").val("");
					}
	             });
	             
	              
	        });
	      
		</script>
	</head> 
	
	<body> 
		<div data-role="page">
            <div data-role="header" data-theme="b">
                <a id ="mainpageButton" data-icon="back" rel="external">Back</a>
				<h1>Chat Chit</h1>
			</div>
		
			<div data-role="content">
		    	<div id="showContent"></div>
		        <div id="sendMessagePart">
		        	<input type="text" name="text" id="mesg" value="" placeholder="Message" data-theme="d"/>
		            <input type="button" id="sendButton" value="Send" data-theme="b" />
		            <a id ="mainpageButton1" type="button" rel="external" data-theme="b">Back</a>
				</div>
			</div>
		
		</div>
	</body>
</html>
