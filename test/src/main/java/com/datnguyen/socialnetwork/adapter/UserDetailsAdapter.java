package com.datnguyen.socialnetwork.adapter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import com.datnguyen.socialnetwork.model.Users;

public class UserDetailsAdapter implements UserDetails {
	private Users user;

	public UserDetailsAdapter(Users user) { this.user = user; }

	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl("user_role"));
		return authorities;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getPassword() { return user.getPassword();}

	public String getUsername() { return user.getUserName();}

	public boolean isEnabled() { return user.isEnabled();}
	
	public String getFirstName() { return user.getFirstName(); }
	
	public String getLastName() { return user.getLastName(); }
	
	public String getEmail() { return user.getEmail();}
	
	public double getLatitude() { return user.getLatitude();}
	
	public double getLongitude() { return user.getLongitude();}
	
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

}
