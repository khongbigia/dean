package com.datnguyen.socialnetwork.model;

import java.util.List;

import org.springframework.web.socket.WebSocketMessage;

public class MessageWebSocketWrap implements WebSocketMessage<MessageWebSocketWrap>{

	private MessageWebSocketWrap message;

	public MessageWebSocketWrap getMessageWebSocketWrap() {
		return message;
	}

	public MessageWebSocketWrap getPayload() {
		// TODO Auto-generated method stub
		return message;
	}
	public int getPayloadLength() {
		// TODO Auto-generated method stub
		return 0;
	}
	public boolean isLast() {
		// TODO Auto-generated method stub
		return false;
	}
    
    
}
