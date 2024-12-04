package com.aes.eventmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aes.eventmanagementsystem.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role getByRoleName(String roleName);
}
