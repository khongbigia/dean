
package com.datnguyen.socialnetwork.controller;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.datnguyen.socialnetwork.service.ConfirmationExpiredException;
import com.datnguyen.socialnetwork.service.ConfirmationFailedException;
import com.datnguyen.socialnetwork.service.UsersService;
import com.datnguyen.socialnetwork.service.impl.UsersMailSender;


@Controller
@RequestMapping(value = "/")
public class UserController {
	@Inject private UsersService usersService;
	
    @RequestMapping(value = "/newpassword", method = RequestMethod.GET)
    public String newPassword(    	
    	@RequestParam("u") String userName,
    	@RequestParam("d") String digest, Model model) {
    	try {
    			usersService.confirmSignup(userName, digest);
    			model.addAttribute("username", userName);
    			return "newpassword";
    		} 
    	catch (ConfirmationExpiredException e) {model.addAttribute("expired", true);}
    	catch (ConfirmationFailedException e) {model.addAttribute("failed", true);}
    	return "redirect:/signup";
 	}
    
    @RequestMapping(value = "/newpasswordSubmit", method = RequestMethod.POST)
    public String newPasswordSubmit(    	
    	@RequestParam("username") String userName,
    	@RequestParam("password") String password) {
    	System.out.print(userName + password);
    	usersService.updateNewPassword(userName, password);
    	return "new_password_success";
 	}
    
    @RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
    public String forgotPassword(){    	
    	return "forgotpassword";
 	}
    
    @RequestMapping(value = "/submitRecoverPassword", method = RequestMethod.POST)
    public String submitRecoverPasswordSuccess(@RequestParam(value="username") String userName) {
		usersService.sendForgotPasswordEmail(userName);
		return "submitRecoverPasswordSuccess";
    }
    
    @RequestMapping(value = "/sign_up_success", method = RequestMethod.GET)
    public String sign_up_success(){    	
    	return "sign_up_success";
 	}
    
    @RequestMapping(value = "/mainpage", method = RequestMethod.GET)
    public String mainpage(Model model){   
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        model.addAttribute("avatarpath", "/test/avatar/"+name);
        model.addAttribute("username", name);
        model.addAttribute("sharelocation", usersService.getUserByUserName(name).isShareLocation());
    	return "mainpage";
 	}
}
