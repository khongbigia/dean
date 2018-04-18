/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datnguyen.socialnetwork.service;

import java.util.List;

import com.datnguyen.socialnetwork.model.Messages;


public interface MessagesService {
    public void createNewMessages(Messages message);
    public void saveOrUpdateMessages(Messages message);
    public List<Messages> getUnseenMessages(String sender, String receiver);
    public boolean hasUnseenMessages(String userName);
    public List<String> getListFriendsSentMessage(String userName);
    public List<Messages> getSomeOldMessages(String sender, String receiver, String d);
}
