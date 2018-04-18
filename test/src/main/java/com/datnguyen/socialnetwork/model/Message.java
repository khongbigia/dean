package com.datnguyen.socialnetwork.model;

import java.util.ArrayList;
import java.util.List;

public class Message {
	/*type: 
	0 --> friend req notification.(from c to s)
	1 --> friend req notification.(from s to c)
	2 --> message notification (from c to s)
	3 --> message notification (from s to c) */
	
	private int type;
	private String sender;
	private List<String> receiver = new ArrayList<String>();
    private String content;
    
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public List<String> getReceiver() {
		return receiver;
	}
	public void setReceiver(List<String> receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}   
}
