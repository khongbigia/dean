/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datnguyen.socialnetwork.service;

import java.util.List;
import java.util.Map;

import com.datnguyen.socialnetwork.model.Groupsandusers;

public interface GroupsandusersService {
    public void createNewUserWithGroup(Groupsandusers Groupsandusers);
    public void deleteUserWithGroup(Groupsandusers Groupsandusers);
    public boolean isMakeFriendRequest(String userName);
    public Map<Integer,String> getAllListWaitingFriendAccept(String userName);
    public boolean updateFriendRequestToAccept(int id);
    public boolean denyFriendRequest(int id);
    public List<String> getListFriendAccepted(String groupname);
    
    
}
