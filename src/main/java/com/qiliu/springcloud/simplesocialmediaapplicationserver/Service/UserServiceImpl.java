package com.qiliu.springcloud.simplesocialmediaapplicationserver.Service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.qiliu.springcloud.simplesocialmediaapplicationserver.Model.User;
import com.qiliu.springcloud.simplesocialmediaapplicationserver.Source.RoleRepository;
import com.qiliu.springcloud.simplesocialmediaapplicationserver.Source.UserRepository;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
   

	@Override
	public void createUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    user.setRoles(new HashSet<>(roleRepository.findAll()));
	    userRepository.save(user);				
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
		
	}

	@Override
	public User getUserByName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
		
	}
     
	
}
