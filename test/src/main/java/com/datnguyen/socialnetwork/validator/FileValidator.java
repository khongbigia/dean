package com.datnguyen.socialnetwork.validator;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.datnguyen.socialnetwork.model.File;

@Component
public class FileValidator implements Validator {
	public boolean supports(Class<?> paramClass) {
		return File.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
		File file = (File) obj;
		  if ((file.getFile().getSize() == 0 ) || (file.getFile() == null)) {
		   errors.rejectValue("file", "valid.file");
		  }
	}
}
