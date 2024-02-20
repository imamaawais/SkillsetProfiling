package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Role_Repo extends JpaRepository<Role, Integer> {
   //Optional<Role> findByRoleID(String RoleID);
}
