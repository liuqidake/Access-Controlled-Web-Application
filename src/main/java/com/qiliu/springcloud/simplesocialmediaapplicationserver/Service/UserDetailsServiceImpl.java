package com.qiliu.springcloud.simplesocialmediaapplicationserver.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.qiliu.springcloud.simplesocialmediaapplicationserver.Model.Role;
import com.qiliu.springcloud.simplesocialmediaapplicationserver.Model.User;

public class UserDetailsServiceImpl implements UserDetailsService {
    
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserByName(username);
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		
		for(Role role: user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),authorities);
	}

}
