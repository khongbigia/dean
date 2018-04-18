
package com.datnguyen.socialnetwork.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.datnguyen.socialnetwork.model.Avatar;
import com.datnguyen.socialnetwork.model.File;
import com.datnguyen.socialnetwork.service.AvatarService;
import com.datnguyen.socialnetwork.service.ConfirmationExpiredException;
import com.datnguyen.socialnetwork.service.ConfirmationFailedException;
import com.datnguyen.socialnetwork.service.UsersService;
import com.datnguyen.socialnetwork.service.impl.UsersMailSender;
//import com.datnguyen.socialnetwork.validator.FileValidator;


@Controller
@RequestMapping
@Scope("session")
public class ChatController {
	@Inject private UsersService usersService;
	
	@RequestMapping(value = "/user/chatMainPage", method = RequestMethod.GET)
	public String chatMainPage(Model model){    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("avatarpath", "/test/avatar/"+name);
        model.addAttribute("username", name);
        model.addAttribute("sharelocation", usersService.getUserByUserName(name).isShareLocation());
    	return "user/chat/chatMainPage";
 	}
	
	@RequestMapping(value = "/user/chatOneOneList", method = RequestMethod.GET)
	public String chatOneOneList(Model model, HttpServletRequest request){    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("avatarpath", "/test/avatar/"+name);
        model.addAttribute("username", name);
        model.addAttribute("sharelocation", usersService.getUserByUserName(name).isShareLocation());
        request.getSession().setAttribute("caller","chatOneOneList");
    	return "user/chat/chatOneOneList";
 	}
	
	@RequestMapping(value = "/user/chatWithSomeone", method = RequestMethod.GET)
	public String chatWithSomeone(Model model, @RequestParam("receiver") String receiver, HttpServletRequest request){    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("avatarpath", "/test/avatar/"+name);
        model.addAttribute("avatarreceiverpath", "/test/avatar/"+receiver);
        model.addAttribute("username", name);
        model.addAttribute("receiver", receiver);
        model.addAttribute("sharelocation", usersService.getUserByUserName(name).isShareLocation());
        
        String caller = (String) request.getSession().getAttribute("caller");
        model.addAttribute("caller", caller);
    	return "user/chat/chatWithSomeone";
 	}
	
	@RequestMapping(value = "/user/messageBox", method = RequestMethod.GET)
	public String messageBox(Model model, HttpServletRequest request){    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("avatarpath", "/test/avatar/"+name);
        model.addAttribute("username", name);
        model.addAttribute("sharelocation", usersService.getUserByUserName(name).isShareLocation());
        request.getSession().setAttribute("caller","messageBox");
    	return "user/chat/messageBox";
 	}
}
