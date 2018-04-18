package com.datnguyen.socialnetwork.validator;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.datnguyen.socialnetwork.form.UsersForm;

@Service
public class ConfirmPasswordValidator implements Validator {
 
    //which objects can be validated by this validator
    public boolean supports(Class<?> paramClass) {
        return UsersForm.class.equals(paramClass);
    }
 
    public void validate(Object obj, Errors errors) {
    	//void org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace(Errors errors, String field, String errorCode)
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
         
    	UsersForm usersForm = (UsersForm) obj;
        if(!usersForm.getPassword().equals(usersForm.getConfirmedPassword())){
        	errors.rejectValue("confirmedPassword", "password.mismatch", null, "Password not match");
        }
         
    }

}