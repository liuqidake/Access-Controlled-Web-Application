package com.qiliu.springcloud.simplesocialmediaapplicationserver.Service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.qiliu.springcloud.simplesocialmediaapplicationserver.Model.Role;
import com.qiliu.springcloud.simplesocialmediaapplicationserver.Model.User;
import com.qiliu.springcloud.simplesocialmediaapplicationserver.Source.RoleRepository;
import com.qiliu.springcloud.simplesocialmediaapplicationserver.Source.UserRepository;


@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setEnabled(1);	
		Role role = roleRepository.findByName("USER");		
		user.setRoles(new HashSet<>(Arrays.asList(role)));
		userRepository.save(user);
		
	}
   


     
	
}
