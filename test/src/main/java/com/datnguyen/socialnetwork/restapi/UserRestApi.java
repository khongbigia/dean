
package com.datnguyen.socialnetwork.restapi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.datnguyen.socialnetwork.model.Groupsandusers;
import com.datnguyen.socialnetwork.model.Messages;
import com.datnguyen.socialnetwork.model.Users;
import com.datnguyen.socialnetwork.service.GroupsandusersService;
import com.datnguyen.socialnetwork.service.MessagesService;
import com.datnguyen.socialnetwork.service.UsersService;
import com.datnguyen.socialnetwork.service.impl.UsersMailSender;


@RestController
public class UserRestApi {
	@Inject private UsersService usersService;
	@Inject private GroupsandusersService groupsandusersService;
	@Inject private MessagesService messagesService;
	//Another possibility which may be easier in some circumstances is to add a trailing / to the end of the URL.
	//. --> parse as extension
	@RequestMapping(value="user/updatelatlong/{latitude}/{longitude}", method=RequestMethod.PUT)
    public void updateLatLong(@PathVariable("latitude") double latitude, @PathVariable("longitude") double longitude) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
		Users user = usersService.getUserByUserName(userName);
		user.setLatitude(latitude);
		user.setLongitude(longitude);
		usersService.updateUser(user);
    }
	
	@RequestMapping(value="user/updatesharelocationstatus/{status}", method=RequestMethod.PUT)
    public void updateShareLocationStatus(@PathVariable("status") String status) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
		Users user = usersService.getUserByUserName(userName);
		boolean stat;
		if (status.equals("true")){
			stat=true;
		}
		else{
			stat=false;
		}
			
		user.setShareLocation(stat);;
		usersService.updateUser(user);
    }
	
	@RequestMapping(value="user/getAllUseridExceptYou", method=RequestMethod.GET)
    public void getAllUseridExceptYou(@PathVariable("status") String status) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
		Users user = usersService.getUserByUserName(userName);
		boolean stat;
		if (status.equals("true")){
			stat=true;
		}
		else{
			stat=false;
		}
			
		user.setShareLocation(stat);
		usersService.updateUser(user);
    }
	
	
	@RequestMapping(value="user/listofusersnotfriendnotyou", method=RequestMethod.GET)
    public List<String> listOfUsersNotFriend() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        //String groupname = "Special " + userName;
        String groupname = userName;
        List<String> l = usersService.getAllUserNamesNotFriend(groupname);
        l.remove(userName);
		return l;
    }
	
	@RequestMapping(value="user/getListFriendsWaitingAccept", method=RequestMethod.GET)
    public Map<Integer,String> getListFriendsWaitingAccept() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Map<Integer,String> l = groupsandusersService.getAllListWaitingFriendAccept(userName);
		return l;
    }
	
	@RequestMapping(value="user/getListFriendAccepted", method=RequestMethod.GET)
    public List<String> getListFriendAccepted() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        List<String> l = groupsandusersService.getListFriendAccepted(userName);
		return l;
    }
	
	
	@RequestMapping(value="user/makefriendrequest/{friendname}", method=RequestMethod.POST)
    public void makeFriendRequest(@PathVariable("friendname") String friendName) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        //String groupname = "Special " + userName;
        String groupname = userName;
        Groupsandusers groupsandusers = new Groupsandusers();
        groupsandusers.setGroupname(groupname);
        groupsandusers.setStatus("waiting accept");
        groupsandusers.setUsername(friendName);
        groupsandusersService.createNewUserWithGroup(groupsandusers);
    }
	
	@RequestMapping(value="user/updateFriendRequestToAccept/{id}", method=RequestMethod.PUT)
    public void updateFriendRequestToAccept(@PathVariable("id") int id) {
		groupsandusersService.updateFriendRequestToAccept(id);
    }
	
	@RequestMapping(value="user/denyFriendRequest/{id}", method=RequestMethod.DELETE)
    public void denyFriendRequest(@PathVariable("id") int id) {
		groupsandusersService.denyFriendRequest(id);
    }
	
	@RequestMapping(value="user/getUnseenMessages/{sender}", method=RequestMethod.GET)
    public List<Messages> getUnseenMessages(@PathVariable("sender") String sender) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String receiver = auth.getName();			
        List<Messages> l = messagesService.getUnseenMessages(sender, receiver);
        List<String> res = new ArrayList<String>();
        for (Messages message : l){
        	res.add(message.getContent());
        	message.setStatus("Seen");
        	messagesService.saveOrUpdateMessages(message);
        }
        
		return l;
    }
	
	@RequestMapping(value="user/getSomeOldMessages/{sender}/{d}", method=RequestMethod.GET)
	public List<Messages> getSomeOldMessages(@PathVariable("sender") String sender, @PathVariable("d") String d) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String receiver = auth.getName();			
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        Long tl = Long.parseLong(d);
        Date date1 = new Date(tl);
        String dt = simpleDateFormat.format(date1);
        List<Messages> l = messagesService.getSomeOldMessages(sender, receiver, dt);
		return l;
    }
	
	@RequestMapping(value="user/getListFriendsSentMessage", method=RequestMethod.GET)
    public List<String> getListFriendsSentMessage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        List<String> l = messagesService.getListFriendsSentMessage(userName);
        l.remove(userName);
		return l;
    }
	
	@RequestMapping(value="user/getListUserEntityFromListUserName/{listUserName}", method=RequestMethod.GET)
    public List<Users> getListUserEntityFromListUserName(@PathVariable("listUserName") String listUserName) {
		//Need to verify friend relationship --> future
        List<Users> l = new ArrayList<Users>();
        String[] listUsers = listUserName.split(",");
        for (String userName: listUsers)
        {
           l.add(usersService.getUserByUserName(userName));
        }
		return l;
    }
}
