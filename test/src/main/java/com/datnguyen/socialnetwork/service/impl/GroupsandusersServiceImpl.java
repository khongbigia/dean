/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datnguyen.socialnetwork.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.datnguyen.dao.AvatarDao;
import com.datnguyen.dao.GroupsandusersDao;
import com.datnguyen.dao.UsersDao;
import com.datnguyen.socialnetwork.adapter.UserDetailsAdapter;
import com.datnguyen.socialnetwork.model.Avatar;
import com.datnguyen.socialnetwork.model.Groupsandusers;
//import com.datnguyen.socialnetwork.adapter.UserDetailsAdapter;
import com.datnguyen.socialnetwork.model.Users;
import com.datnguyen.socialnetwork.service.ConfirmationExpiredException;
import com.datnguyen.socialnetwork.service.ConfirmationFailedException;
import com.datnguyen.socialnetwork.service.GroupsandusersService;
import com.datnguyen.socialnetwork.service.UsersService;































import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.velocity.app.VelocityEngine;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class GroupsandusersServiceImpl implements GroupsandusersService {

	@Inject private GroupsandusersDao groupsandusersDao;
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public void createNewUserWithGroup(Groupsandusers groupsandusers) {
		groupsandusersDao.create(groupsandusers);
		
	}

	public void deleteUserWithGroup(Groupsandusers Groupsandusers) {
		// TODO Auto-generated method stub
		
	}

	public boolean isMakeFriendRequest(String userName) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("groupsandusers.isMakeFriendRequest").setString("username", userName);
		List<?> list = query.list();
		if (!list.isEmpty())
			return true;
		else
			return false;
	}

	public Map<Integer,String> getAllListWaitingFriendAccept(String userName) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("groupsandusers.getAllListWaitingFriendAccept").setString("username", userName);
		List l = query.list();
		//List<String> res = new ArrayList<String>();
		Iterator it = l.iterator();
		Map<Integer,String> res = new HashMap<Integer,String>();
		while(it.hasNext())
		{
			Groupsandusers groupsandusers = (Groupsandusers)it.next();
			String name = groupsandusers.getGroupname();
			//String[] splitRes = name.split(" ");
			//res.put(groupsandusers.getId(),splitRes[1]);
			res.put(groupsandusers.getId(),name);
		}
		return res;
	}

	public boolean updateFriendRequestToAccept(int id) {
		Groupsandusers groupsandusers = groupsandusersDao.get(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
		if (groupsandusers.getUsername().equals(name)){
			groupsandusers.setStatus("accepted");
			groupsandusersDao.update(groupsandusers);
			Groupsandusers groupsandusersOther = new Groupsandusers();
			groupsandusersOther.setUsername(groupsandusers.getGroupname());
			groupsandusersOther.setGroupname(groupsandusers.getUsername());
			groupsandusersOther.setStatus("accepted");
			groupsandusersDao.create(groupsandusersOther);
			return true;
		}
		else
			return false;
	}

	public boolean denyFriendRequest(int id) {
		Groupsandusers groupsandusers = groupsandusersDao.get(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
		if (groupsandusers.getUsername().equals(name)){
			groupsandusersDao.deleteById(id);
			return true;
		}
		else
			return false;
	}

	public List<String> getListFriendAccepted(String groupname) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("groupsandusers.getListFriendAccepted").setString("groupname", groupname);
        List l = query.list();
        Iterator it = l.iterator();
        List<String> res = new ArrayList<String>();
        String tmp ="";
		while(it.hasNext())
		{
			tmp = (String)it.next();
			res.add(tmp);
			System.out.println(tmp);
		}
		return res;
	}
    
}
