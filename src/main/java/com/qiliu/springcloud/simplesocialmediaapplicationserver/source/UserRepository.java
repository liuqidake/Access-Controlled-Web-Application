package com.qiliu.springcloud.simplesocialmediaapplicationserver.source;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qiliu.springcloud.simplesocialmediaapplicationserver.Model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
