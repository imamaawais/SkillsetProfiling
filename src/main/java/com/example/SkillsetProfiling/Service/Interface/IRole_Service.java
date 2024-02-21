package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Role_DTO;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface IRole_Service {
    Role_DTO addRole(Role_DTO roleDTO);
    List<Role_DTO> getAllRoles();
    Role_DTO getRoleByRoleID(Integer roleId) throws RoleNotFoundException;
    //Role_DTO getRoleByRoleName(String roleName) throws RoleNotFoundException;
    Role_DTO updateRole(Integer roleId, Role_DTO updatedRoleDTO) throws RoleNotFoundException;
    boolean deleteRole(Integer roleId) throws RoleNotFoundException ;
}
