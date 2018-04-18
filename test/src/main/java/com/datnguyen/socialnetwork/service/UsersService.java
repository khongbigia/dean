/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datnguyen.socialnetwork.service;

import java.util.ArrayList;
import java.util.List;

import com.datnguyen.socialnetwork.model.Users;


public interface UsersService {
    public void createNewUser(Users user);
    public void confirmSignup(String userName, String digest) throws ConfirmationFailedException;
    public Users getUserByUserName(String userName);
    public void sendForgotPasswordEmail(String userName);
    public void updateNewPassword(String userName, String password);
    public void updateUser(Users user);
    public List<String> getAllUserNames();
    public List<String> getAllUserNamesNotFriend(String groupname);
}
