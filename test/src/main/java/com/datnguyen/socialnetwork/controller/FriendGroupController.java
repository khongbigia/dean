
package com.datnguyen.socialnetwork.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.inject.Inject;

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
public class FriendGroupController {
	@Inject private UsersService usersService;
	
	@RequestMapping(value = "/user/makefriend", method = RequestMethod.GET)
	public String makefriend(Model model){    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("avatarpath", "/test/avatar/"+name);
        model.addAttribute("username", name);
        model.addAttribute("sharelocation", usersService.getUserByUserName(name).isShareLocation());
    	return "user/friendgroup/makeFriends";
 	}
	
	@RequestMapping(value = "/user/listFriendsWaitingAccept", method = RequestMethod.GET)
	public String listFriendsWaitingAccept(Model model){    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("avatarpath", "/test/avatar/"+name);
        model.addAttribute("username", name);
        model.addAttribute("sharelocation", usersService.getUserByUserName(name).isShareLocation());
    	return "user/friendgroup/listFriendsWaitingAccept";
 	}
		
	@RequestMapping(value = "/user/makeFriendConfirmation", method = RequestMethod.GET)
	public String makeFriendConfirmation(Model model, @RequestParam(value = "friendname") String friendname){    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("avatarpath", "/test/avatar/"+name);
        model.addAttribute("username", name);
        model.addAttribute("friendname", friendname);
        model.addAttribute("sharelocation", usersService.getUserByUserName(name).isShareLocation());
    	return "user/friendgroup/makeFriendConfirmation";
 	}
	
	@RequestMapping(value = "/user/friendRequestDecision", method = RequestMethod.GET)
	public String friendRequestDecision(Model model, @RequestParam(value = "id") int id){    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("avatarpath", "/test/avatar/"+name);
        model.addAttribute("username", name);
        model.addAttribute("id", id);
        model.addAttribute("sharelocation", usersService.getUserByUserName(name).isShareLocation());
    	return "user/friendgroup/friendRequestDecision";
 	}
	
}
