package com.datnguyen.socialnetwork.websocket;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.datnguyen.socialnetwork.model.Message;
import com.datnguyen.socialnetwork.model.Messages;
import com.datnguyen.socialnetwork.service.GroupsandusersService;
import com.datnguyen.socialnetwork.service.MessagesService;
import com.google.gson.Gson;
@Service
public class WebsocketEndPoint extends TextWebSocketHandler {
	@Inject private GroupsandusersService groupsandusersService;
	@Inject private MessagesService messagesService;
	public static Map<String, WebSocketSession> userConnected = new  HashMap<String, WebSocketSession>();
	public static Gson gson = new Gson();
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Message m = gson.fromJson(message.getPayload(), Message.class);
		//0 --> friend req notification.(from c to s)
		if (m.getType()==0){
			//System.out.println(m.getReceiver().get(0));
			Message mTo = new Message();
			mTo.setType(1); // 1 --> friend req notification.(from s to c)
			String m_json = gson.toJson(mTo);
			userConnected.get(m.getReceiver().get(0)).sendMessage(new TextMessage(m_json));
		}
		
		//2 --> message notification (from c to s)
		if (m.getType()==2){
			Messages mesg = new Messages();
			mesg.setSender(m.getSender());
			mesg.setReceiver(m.getReceiver().get(0));
			mesg.setContent(m.getContent());
			mesg.setStatus("Unseen");
			mesg.setDateCreated(new Date());
			messagesService.createNewMessages(mesg);
			
			if (userConnected.containsKey(m.getReceiver().get(0))){
				Message mTo = new Message();
				mTo.setType(3); // 3 --> message notification (from s to c)
				String m_json = gson.toJson(mTo);
				userConnected.get(m.getReceiver().get(0)).sendMessage(new TextMessage(m_json));
			}
		}

	}
	
	 @Override
	 public void afterConnectionEstablished(WebSocketSession session) throws IOException{

		 userConnected.put(session.getPrincipal().getName(), session);
	     String userName = session.getPrincipal().getName();
		 if (groupsandusersService.isMakeFriendRequest(userName)){
			 Message m = new Message();
			 m.setType(1); // 1 --> friend req notification.(from s to c)
			 String m_json = gson.toJson(m);
			 userConnected.get(userName).sendMessage(new TextMessage(m_json));
		 }
		 if (messagesService.hasUnseenMessages(userName)){
			 Message m = new Message();
			 m.setType(3); // 3 --> message notification (from s to c)
			 String m_json = gson.toJson(m);
			 userConnected.get(userName).sendMessage(new TextMessage(m_json));
		 }
	 }
	 
	 @Override
	 public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus){
		 userConnected.remove(session.getPrincipal());
	 }
}
