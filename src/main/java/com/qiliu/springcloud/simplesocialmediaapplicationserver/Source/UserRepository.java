package com.qiliu.springcloud.simplesocialmediaapplicationserver.Source;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qiliu.springcloud.simplesocialmediaapplicationserver.Model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);
}
