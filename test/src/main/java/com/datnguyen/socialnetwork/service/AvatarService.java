/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datnguyen.socialnetwork.service;

import com.datnguyen.socialnetwork.model.Avatar;
import com.datnguyen.socialnetwork.model.Users;


public interface AvatarService {
    public void createNewAvatar(Avatar avatar);
    public void saveOrUpdateAvatar(Avatar avatar);
    public Avatar getAvatarByUserName(String userName);
    public void createOverlay(String userName);
}
