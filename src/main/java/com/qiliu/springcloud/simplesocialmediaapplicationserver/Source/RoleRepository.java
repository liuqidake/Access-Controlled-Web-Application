package com.qiliu.springcloud.simplesocialmediaapplicationserver.Source;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qiliu.springcloud.simplesocialmediaapplicationserver.Model.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long>{
	
    Role findByName(String name);
}
