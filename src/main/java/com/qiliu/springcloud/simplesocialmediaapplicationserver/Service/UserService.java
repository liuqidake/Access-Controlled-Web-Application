package com.qiliu.springcloud.simplesocialmediaapplicationserver.Service;

import com.qiliu.springcloud.simplesocialmediaapplicationserver.Model.User;

public interface UserService {
    
	void createUser(User user);
	
	void updateUser(User user);
	
	User getUserByName(String name);
	
	void deleteUser(User user);
}
