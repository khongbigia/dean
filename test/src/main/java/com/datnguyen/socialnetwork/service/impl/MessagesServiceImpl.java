/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datnguyen.socialnetwork.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.datnguyen.dao.AvatarDao;
import com.datnguyen.dao.MessagesDao;
import com.datnguyen.dao.UsersDao;
import com.datnguyen.socialnetwork.adapter.UserDetailsAdapter;
import com.datnguyen.socialnetwork.model.Avatar;
//import com.datnguyen.socialnetwork.adapter.UserDetailsAdapter;

import com.datnguyen.socialnetwork.model.Messages;
import com.datnguyen.socialnetwork.service.AvatarService;
import com.datnguyen.socialnetwork.service.ConfirmationExpiredException;
import com.datnguyen.socialnetwork.service.ConfirmationFailedException;
import com.datnguyen.socialnetwork.service.MessagesService;
import com.datnguyen.socialnetwork.service.UsersService;


























import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.velocity.app.VelocityEngine;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class MessagesServiceImpl implements MessagesService {
	@Inject private MessagesDao messagesDao;
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public void createNewMessages(Messages message) {
		messagesDao.create(message);
		
	}

	public void saveOrUpdateMessages(Messages message) {
		messagesDao.saveOrUpdate(message);
		
	}

	public List<Messages> getUnseenMessages(String sender, String receiver) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("Messages.findUnseenMessages");
		query.setString("sender", sender);
		query.setString("receiver", receiver);
        List l = query.list();
        Iterator it = l.iterator();
        List<Messages> res = new ArrayList<Messages>();
		while(it.hasNext())
		{
			res.add((Messages)it.next());

		}
		return res;
	}

	public boolean hasUnseenMessages(String userName) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("Messages.hasUnseenMessages");
		query.setString("receiver", userName);
		if ((query.list()==null) || (query.list().size()<1)){
			return false;
		}
		else{
			return true;
		}
	}

	public List<String> getListFriendsSentMessage(String userName) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("Messages.getListFriendsSentMessage");
		query.setString("receiver", userName);
		List l = query.list();
        Iterator it = l.iterator();
        List<String> res = new ArrayList<String>();
		while(it.hasNext())
		{
			res.add((String)it.next());
		}
		return res;
	}

	public List<Messages> getSomeOldMessages(String sender, String receiver, String d) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM Messages where (Messages.datecreated < :d) and ((Messages.sender = :sender and Messages.receiver = :receiver) or (Messages.sender = :receiver and Messages.receiver = :sender)) order by Messages.datecreated desc limit 4").addEntity(Messages.class);
		query.setString("sender", sender);
		query.setString("receiver", receiver);
		query.setString("d", d);
        List l = query.list();
        Iterator it = l.iterator();
        List<Messages> res = new ArrayList<Messages>();
		while(it.hasNext())
		{
			res.add((Messages)it.next());
			//System.out.println(it);
		}
		return res;
	}


}
