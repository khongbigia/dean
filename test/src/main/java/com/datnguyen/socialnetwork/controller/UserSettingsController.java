
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
import com.datnguyen.socialnetwork.validator.FileValidator;


@Controller
//@PreAuthorize("hasRole('user_role')")
//@SessionAttributes({"latitude","longitude"})
@RequestMapping(value = "/userSettings")
public class UserSettingsController {
	
	@Inject FileValidator fileValidator;
	@Inject private AvatarService avatarService;
	@Inject private UsersService usersService;
	
    @RequestMapping(value = "/mainpage", method = RequestMethod.GET)
    public String mainpage(Model model){    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        model.addAttribute("avatarpath", "/test/avatar/"+name);
        model.addAttribute("username", name);
        model.addAttribute("sharelocation", usersService.getUserByUserName(name).isShareLocation());
    	return "user/userSettings/UserSettingsMainPage";
 	}
    
    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String changePassword(Model model){    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        model.addAttribute("avatarpath", "/test/avatar/"+name);
        model.addAttribute("username", name);
        model.addAttribute("sharelocation", usersService.getUserByUserName(name).isShareLocation());
    	return "user/userSettings/changePassword";
 	}
    
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFileHandler(@Validated @ModelAttribute("file") File file, BindingResult result, Model model) throws IOException {
    	
    	fileValidator.validate(file, result);
    	if (result.hasErrors()) {
    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            model.addAttribute("avatarpath", "/test/avatar/"+name);
            model.addAttribute("username", name);
            model.addAttribute("sharelocation", usersService.getUserByUserName(name).isShareLocation());
    		return "user/userSettings/uploadImage";
    	} 
    	else 
    	{           
    		MultipartFile fileUpload = file.getFile();
    		if (fileUpload  != null && fileUpload.getSize() > 0) {
	    		Avatar avatar = new Avatar();
	         	avatar.setAvatar(fileUpload.getBytes());
	         	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	            String name = auth.getName(); //get logged in username
	            avatar.setUserName(name);
	         	avatarService.saveOrUpdateAvatar(avatar);         
	         	avatarService.createOverlay(name);
    		}
    	}

    	return "redirect:uploadImage";
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload(){    	
    	return "user/userSettings/upload";
 	}
    
    @RequestMapping(value = "/uploadImage", method = RequestMethod.GET)
    public String uploadImage(Model model){    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("avatarpath", "/test/avatar/"+name);
        model.addAttribute("username", name);
        model.addAttribute("sharelocation", usersService.getUserByUserName(name).isShareLocation());
        File fileModel = new File();
        model.addAttribute("file", fileModel);
    	return "user/userSettings/uploadImage";
 	}
    
    @RequestMapping(value = "/newpasswordSubmit", method = RequestMethod.POST)
    public String newPasswordSubmit(    	
    	@RequestParam("username") String userName,
    	@RequestParam("password") String password) {
    	System.out.print(userName + password);
    	usersService.updateNewPassword(userName, password);
    	return "user/userSettings/new_password_success";
 	}
    
    @RequestMapping(value = "/locationSetting", method = RequestMethod.GET)
    public ModelAndView locationSetting(ModelAndView  modelAndView ){    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        modelAndView.addObject("avatarpath", "/test/avatar/"+name);
        modelAndView.addObject("username", name);
        modelAndView.addObject("sharelocation", usersService.getUserByUserName(name).isShareLocation());
        //modelAndView.addObject("latitude",1000);
        //modelAndView.addObject("longitude",1000);
        modelAndView.setViewName("user/userSettings/locationSetting");
    	return modelAndView;
 	}
    

}
