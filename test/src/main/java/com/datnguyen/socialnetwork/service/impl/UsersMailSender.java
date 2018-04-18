
package com.datnguyen.socialnetwork.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;




import com.datnguyen.socialnetwork.model.Users;

@Component
public class UsersMailSender {
	private static final String CONFIRMATION_TEMPLATE_PATH = "userConfirmEmail.vm";
	private static final String USER_MSG_TEMPLATE_PATH = "newUserNotify.vm";
	private static final String FORGOT_PASSWORD_USER_NOTIFY_TEMPLATE_PATH = "forgotPasswordUserNotify.vm";
	private static final String FORGOT_PASSWORD_USER_TEMPLATE_PATH = "userForgotPasswordEmail.vm";
	
	@Inject private JavaMailSender mailSender;
	@Inject private VelocityEngine velocityEngine;
	
	@Value("#{contactServiceProps.sendConfirmation}")
	private boolean sendConfirmation;
	
	@Value("#{contactServiceProps.notifyAdmin}")
	private boolean notifyAdmin;
	
	@Value("#{contactServiceProps.noReplyEmailAddress}")
	private String noReplyEmailAddr;
	
	@Value("#{contactServiceProps.adminEmailAddress}")
	private String adminEmailAddr;
	
	@Value("#{contactServiceProps.confirmationKey}")
	private String confirmationKey;
	
	@Value("#{contactServiceProps.confirmSignupUrl}")
	private String confirmSignupUrl;
	
	@Value("#{contactServiceProps.forgotPasswordUrl}")
	private String forgotPasswordUrl;
	
	@Value("#{contactServiceProps.sendForgotPassword}")
	private boolean sendForgotPassword;
	
	@Async
	public void sendEmail(Users user) {
		if (sendConfirmation) {
			MimeMessage mimeMsg = createEmail(
					user, CONFIRMATION_TEMPLATE_PATH,
					"Sign-up Confirmation", user.getEmail(),
					noReplyEmailAddr, null);
			sendEmail(mimeMsg);
		}
		if (notifyAdmin) {
			MimeMessage mimeMsg = createEmail(
					user, USER_MSG_TEMPLATE_PATH,
					"User message", adminEmailAddr,
					user.getEmail(), user.getFirstName());
			sendEmail(mimeMsg);
		}
	}
	
	@Async
	public void sendForgotPasswordEmail(Users user) {
		System.out.print(user.getEmail());
		if (sendForgotPassword) {
			MimeMessage mimeMsg = createForgotPasswordEmail(
					user, FORGOT_PASSWORD_USER_TEMPLATE_PATH,
					"FORGOT_PASSWORD_USER_TEMPLATE_PATH", user.getEmail(),
					noReplyEmailAddr, null);
			sendEmail(mimeMsg);
		}
		if (notifyAdmin) {
			MimeMessage mimeMsg = createForgotPasswordEmail(
					user, FORGOT_PASSWORD_USER_NOTIFY_TEMPLATE_PATH,
					"FORGOT_PASSWORD_USER_NOTIFY_TEMPLATE_PATH", adminEmailAddr,
					user.getEmail(), user.getFirstName());
			sendEmail(mimeMsg);
		}
	}
	
	private MimeMessage createEmail(Users user, String templatePath,
			String subject, String toEmail, String fromEmail, String fromName) {
		
		MimeMessage mimeMsg = mailSender.createMimeMessage();
		
		String digest = generateDigest(user);
		String url = confirmSignupUrl + "?u=" + user.getUserName() + "&amp;d=" + digest;
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", user);
		model.put("url", url);
		
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templatePath, model);
		text = text.replaceAll("\n", "<br>");
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg);
			helper.setSubject(subject);
			helper.setTo(toEmail);
			
			if (fromName == null) {
				helper.setFrom(fromEmail);
			} else {
				try {
					helper.setFrom(fromEmail, fromName);
				} catch (UnsupportedEncodingException e) {
					helper.setFrom(fromEmail);
				}
			}
			
			helper.setSentDate(user.getDateCreated());
			helper.setText(text, true);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		return mimeMsg;
	}
	
	private void sendEmail(MimeMessage mimeMsg) {
		mailSender.send(mimeMsg);
	}
	
	private MimeMessage createForgotPasswordEmail(Users user, String templatePath,
			String subject, String toEmail, String fromEmail, String fromName) {
		
		MimeMessage mimeMsg = mailSender.createMimeMessage();
		
		String digest = generateDigest(user);
		String url = forgotPasswordUrl + "?u=" + user.getUserName() + "&amp;d=" + digest;
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", user);
		model.put("url", url);
		
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templatePath, model);
		text = text.replaceAll("\n", "<br>");
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg);
			helper.setSubject(subject);
			helper.setTo(toEmail);
			
			if (fromName == null) {
				helper.setFrom(fromEmail);
			} else {
				try {
					helper.setFrom(fromEmail, fromName);
				} catch (UnsupportedEncodingException e) {
					helper.setFrom(fromEmail);
				}
			}
			
			helper.setSentDate(user.getDateCreated());
			helper.setText(text, true);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		return mimeMsg;
	}
	
	private String generateDigest(Users user) {
    	return DigestUtils.shaHex(user.getUserName() + ":" + confirmationKey);
    }
	
}
