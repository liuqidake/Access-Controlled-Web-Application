package com.qiliu.springcloud.simplesocialmediaapplicationserver.Source;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qiliu.springcloud.simplesocialmediaapplicationserver.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByName(String name);
}
