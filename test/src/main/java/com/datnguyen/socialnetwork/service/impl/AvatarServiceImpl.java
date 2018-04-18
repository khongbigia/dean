/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datnguyen.socialnetwork.service.impl;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.datnguyen.dao.AvatarDao;
import com.datnguyen.dao.UsersDao;
import com.datnguyen.socialnetwork.adapter.UserDetailsAdapter;
import com.datnguyen.socialnetwork.model.Avatar;
//import com.datnguyen.socialnetwork.adapter.UserDetailsAdapter;

import com.datnguyen.socialnetwork.service.AvatarService;
import com.datnguyen.socialnetwork.service.ConfirmationExpiredException;
import com.datnguyen.socialnetwork.service.ConfirmationFailedException;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {
	private static final int IMG_WIDTH = 36;
	private static final int IMG_HEIGHT = 31;
	@Inject private AvatarDao avatarDao;
	
	public void createNewAvatar(Avatar avatar) {
		avatarDao.create(avatar);
		
	}

	public void saveOrUpdateAvatar(Avatar avatar) {
		avatarDao.saveOrUpdate(avatar);
		
	}

	public Avatar getAvatarByUserName(String userName) {		
		return avatarDao.get(userName);
	}

	public void createOverlay(String userName) {
		Avatar avatar = getAvatarByUserName(userName);
		InputStream in = new ByteArrayInputStream(avatar.getAvatar());
		BufferedImage bImageFromConvert;
		try {
			bImageFromConvert = ImageIO.read(in);
			BufferedImage resizeBImageFromConvert = resizeImage(bImageFromConvert, bImageFromConvert.getType());
			BufferedImage overlay = ImageIO.read(new File("C:\\Users\\tommypho\\Documents\\projects\\test\\src\\main\\webapp\\resources\\images\\overla.png"));
			BufferedImage combined = new BufferedImage(overlay.getWidth(), overlay.getHeight(), BufferedImage.TYPE_INT_ARGB);
			// paint both images, preserving the alpha channels
			Graphics g = combined.getGraphics();
			g.drawImage(overlay, 0, 0, null);
			g.drawImage(resizeBImageFromConvert, 5, 5, null);
			ImageIO.write(combined, "PNG", new File("C:\\Users\\tommypho\\Documents\\projects\\test\\src\\main\\webapp\\resources\\markers\\"+userName+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private static BufferedImage resizeImage(BufferedImage originalImage, int type){
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
	 
		return resizedImage;
	}
    
}
