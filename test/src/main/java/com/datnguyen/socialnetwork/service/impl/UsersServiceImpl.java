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
import java.util.Iterator;
import java.util.List;

import com.datnguyen.dao.AvatarDao;
import com.datnguyen.dao.UsersDao;
import com.datnguyen.socialnetwork.adapter.UserDetailsAdapter;
import com.datnguyen.socialnetwork.model.Avatar;
//import com.datnguyen.socialnetwork.adapter.UserDetailsAdapter;
import com.datnguyen.socialnetwork.model.Users;
import com.datnguyen.socialnetwork.service.ConfirmationExpiredException;
import com.datnguyen.socialnetwork.service.ConfirmationFailedException;
import com.datnguyen.socialnetwork.service.UsersService;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.velocity.app.VelocityEngine;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UsersServiceImpl implements UsersService {
    @Inject private UsersDao usersDao;
    @Inject private AvatarDao avatarDao;
    @Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
    @Inject private PasswordEncoder passwordEncoder;
	@Inject private SaltSource saltSource;
	@Inject private UsersMailSender usersMailSender;
	private static final long ONE_DAY_IN_MS = 24 * 60 * 60 * 1000;
	
	@Value("#{contactServiceProps.confirmationKey}")
	private String confirmationKey;
    
    public void createNewUser(Users user) {
    	user.setUserName(user.getUserName().toLowerCase().trim());
    	user.setEmail(user.getEmail().toLowerCase().trim());
    	Object salt = saltSource.getSalt(new UserDetailsAdapter(user));
        String passwordEnc = passwordEncoder.encodePassword(user.getPassword(), salt);
        user.setPassword(passwordEnc);
        user.setDateModified(new Date());
        user.setEnabled(false);
        user.setShareLocation(false);
        user.setLatitude(1000.00);
        user.setLongitude(1000.00);
        usersDao.create(user);
        usersMailSender.sendEmail(user);
        
        URL url;
		try {
			url = new URL("http://localhost:8080/test/resources/images/avatar-image.PNG");
			// read image direct from url
	        BufferedImage image = ImageIO.read(url);

	        // write image to outputstream
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(image, "jpg", baos);
	        baos.flush();

	        // get bytes
	        byte[] avatari = baos.toByteArray();
	        Avatar avatar = new Avatar();
	        avatar.setUserName(user.getUserName());
	        avatar.setAvatar(avatari);
	        avatarDao.create(avatar);
		} 
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }
    
    public void updateNewPassword(String userName, String password) {
    	Users user = usersDao.get(userName);
    	Object salt = saltSource.getSalt(new UserDetailsAdapter(user));
        String passwordEnc = passwordEncoder.encodePassword(password, salt);
        user.setPassword(passwordEnc);
        usersDao.update(user);

    }
    
    private static void checkTimestamp(long timestamp) throws ConfirmationExpiredException {
		long now = System.currentTimeMillis();
		if (now - timestamp > ONE_DAY_IN_MS) {
			throw new ConfirmationExpiredException();
		}
	}

	public void confirmSignup(String userName, String digest) throws ConfirmationFailedException {

		Users user = usersDao.get(userName);
		
		// Check timestamp first to avoid SHA-based DoS.
		checkTimestamp(user.getDateModified().getTime());
		
		// Now generate the digest and check for a match.
		String expectedDigest = generateDigest(user);
		if (!digest.equals(expectedDigest)) {
			throw new ConfirmationFailedException("Bad digest");
		}
		
		user.setEnabled(true);
		usersDao.update(user);
		
	}
	
	private String generateDigest(Users user) {
    	return DigestUtils.shaHex(user.getUserName() + ":" + confirmationKey);
    }

	public Users getUserByUserName(String userName) {
		return usersDao.get(userName);
	}
    
	public void forgotPassword(String userName){
		//getUserByUserName(userName)
		usersDao.update(getUserByUserName(userName));
	}
	
	public void sendForgotPasswordEmail(String userName){
		Query query;
		Users user = usersDao.get(userName);
		if (!(user==null)){
			user.setDateModified(new Date());
			usersDao.update(user);
			usersMailSender.sendForgotPasswordEmail(user);
			System.out.println("ok2");}
		else
		{
			query = sessionFactory.getCurrentSession().getNamedQuery("Users.findUserByEmail").setString("email", userName);
			List<?> list = query.list();
			if (!list.isEmpty()) {
				user = (Users) list.get(0);
				user.setDateModified(new Date());
				usersDao.update(user);
				usersMailSender.sendForgotPasswordEmail(user);
				System.out.println("ok1");}
			else
				System.out.println("nil");
		}

	}

	public void updateUser(Users user) {
		usersDao.update(user);
		
	}

	public List<String> getAllUserNames() {
		List<String> listNames = new ArrayList<String>();
		for (Users user: usersDao.getAll()){
			listNames.add(user.getUserName());
		}
		return listNames;
	}

	public List<String> getAllUserNamesNotFriend(String groupname) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT user_name FROM Users WHERE Users.user_name NOT IN (SELECT user_name FROM groupsandusers where groupsandusers.groupname = :groupname UNION SELECT groupname FROM groupsandusers where groupsandusers.user_name=:groupname)").setParameter("groupname", groupname);
		List l = query.list();
		List<String> res = new ArrayList<String>();
		Iterator it = l.iterator();
		while(it.hasNext())
		{
			String name = (String)it.next();
			res.add(name);
		}
		return res;
	}
}
