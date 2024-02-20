package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Role_DTO;
import com.example.SkillsetProfiling.Entity.Role;

import java.util.List;

public interface IRole_Service {
    Role_DTO addRole(Role_DTO roleDTO);
    List<Role> getAllRoles();
    Role getRoleByName(String roleName);
    Role updateRole(Integer roleId, Role updatedRole);
    boolean deleteRole(Integer roleId);
}
