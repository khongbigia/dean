package com.datnguyen.socialnetwork.service.impl;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datnguyen.socialnetwork.adapter.UserDetailsAdapter;
import com.datnguyen.socialnetwork.model.Users;
import com.datnguyen.socialnetwork.service.UsersService;


@Service("userDetailsService")
@Transactional
public class UserDetailsServiceAdapter implements UserDetailsService {
	@Inject UsersService usersService;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		Users user = usersService.getUserByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("No such user: " + username);
		}

		UserDetailsAdapter userAdapter = new UserDetailsAdapter(user);	
		return userAdapter;
	}
}
