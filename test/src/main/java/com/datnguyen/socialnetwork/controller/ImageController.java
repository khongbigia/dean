
package com.datnguyen.socialnetwork.controller;

import javafx.scene.image.Image;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datnguyen.socialnetwork.service.AvatarService;
import com.datnguyen.socialnetwork.service.ConfirmationExpiredException;
import com.datnguyen.socialnetwork.service.ConfirmationFailedException;
import com.datnguyen.socialnetwork.service.UsersService;
import com.datnguyen.socialnetwork.service.impl.UsersMailSender;



@Controller
@RequestMapping(value = "")
public class ImageController {
	@Inject private AvatarService avatarService;
	
    @RequestMapping(value = "/avatar/{username}")
    @ResponseBody
    public byte[] getAvatar(@PathVariable String username)  {
      return avatarService.getAvatarByUserName(username).getAvatar();
    }
}
