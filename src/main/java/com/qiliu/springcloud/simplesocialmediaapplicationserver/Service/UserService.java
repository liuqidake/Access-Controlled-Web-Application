package com.qiliu.springcloud.simplesocialmediaapplicationserver.Service;

import com.qiliu.springcloud.simplesocialmediaapplicationserver.Model.User;

public interface UserService {
	
	public User findUserByUsername(String username);
	
	public void saveUser(User user); 
}
