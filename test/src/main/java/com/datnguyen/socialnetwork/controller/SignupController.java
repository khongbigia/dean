
package com.datnguyen.socialnetwork.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import com.datnguyen.socialnetwork.form.UsersForm;
import com.datnguyen.socialnetwork.model.Users;
import com.datnguyen.socialnetwork.service.ConfirmationExpiredException;
import com.datnguyen.socialnetwork.service.ConfirmationFailedException;
import com.datnguyen.socialnetwork.service.UsersService;
import com.datnguyen.socialnetwork.validator.ConfirmPasswordValidator;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class SignupController {
    @Inject private UsersService usersService;
    @Inject private ConfirmPasswordValidator confirmPasswordValidator;
    //@Inject ReCaptchaImpl reCaptcha;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	binder.setAllowedFields(new String[] {
    			"userName", "password", "confirmedPassword", "firstName",
    			"lastName", "email"});
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupForm(Model model) {
        model.addAttribute("users", new UsersForm());
        return "signup";
    }
    
    @RequestMapping(value = "/rediectSignup", method = RequestMethod.GET)
    public String rediectSignup(Model model) {
        return "sign_up_success";
    }
    
    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public String signupSubmit(@Valid @ModelAttribute("users") UsersForm users,  BindingResult result, Model model) {
    	Map<String,String> map = new HashMap<String,String>(); 
    	MapBindingResult errors = new MapBindingResult(map, UsersForm.class.getName());
    	//confirmPasswordValidator = new ConfirmPasswordValidator();
    	confirmPasswordValidator.validate(users, errors);
    	List<ObjectError> errorPassword = errors.getAllErrors();
    	if (errorPassword.size()>0)
    		result.addError(errorPassword.get(0));
    	
    	//String remoteAddress = servletRequest.getRemoteAddr();
      /*  ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(remoteAddress, challangeField, responseField);
        if(!reCaptchaResponse.isValid()){
        	result.rejectValue("invalidRecaptcha", "invalid.captcha");
        	model.addAttribute("invalidRecaptcha", true);
        }*/
        
    	if(result.hasErrors()) {
			return "signup";
		}
    	
    	Users user = new Users();
    	user.setUserName(users.getUserName());
    	user.setLastName(users.getLastName());
    	user.setFirstName(users.getFirstName());
    	user.setEmail(users.getEmail());
    	user.setPassword(users.getPassword());
    	try{
    		usersService.createNewUser(user);
        	return "redirect:/rediectSignup";
    	}
    	catch(DataIntegrityViolationException e){
    		model.addAttribute("duplicateIdEmail","duplicate Email or Username");
    		return "signup";
    	}       	
    }
    
    @RequestMapping(value = "/signup-confirm", method = RequestMethod.GET)
    public String confirmSubscription(
    	@RequestParam("u") String userName,
    	@RequestParam("d") String digest, Model model) {
    	try {
    			usersService.confirmSignup(userName, digest);
    			return "login";
    		} 
    	catch (ConfirmationExpiredException e) {model.addAttribute("expired", true);}
    	catch (ConfirmationFailedException e) {model.addAttribute("failed", true);}
    			
    	return "redirect:/signup";
 	}
    
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
 
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
 
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");
 
		return model;
 
	}
}
