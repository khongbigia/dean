/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datnguyen.socialnetwork.dao.hbn;

import com.datnguyen.dao.AvatarDao;
import com.datnguyen.dao.MessagesDao;
import com.datnguyen.dao.UsersDao;
import com.datnguyen.socialnetwork.dao.hibernate.AbstractHbnDao;
import com.datnguyen.socialnetwork.model.Avatar;
import com.datnguyen.socialnetwork.model.Messages;
import com.datnguyen.socialnetwork.model.Users;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




@Repository
@Transactional
public class HbnMessagesDao extends AbstractHbnDao<Messages> implements MessagesDao {
	
	
}
