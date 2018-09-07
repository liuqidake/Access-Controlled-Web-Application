package com.qiliu.springcloud.simplesocialmediaapplicationserver.source;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qiliu.springcloud.simplesocialmediaapplicationserver.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
