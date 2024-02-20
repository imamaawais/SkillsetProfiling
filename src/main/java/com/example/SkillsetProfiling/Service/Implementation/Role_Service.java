package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Role_DTO;
import com.example.SkillsetProfiling.Entity.Role;
import com.example.SkillsetProfiling.Exception.DuplicateRoleException;
import com.example.SkillsetProfiling.Repository.Role_Repo;
import com.example.SkillsetProfiling.Service.Interface.IRole_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Role_Service implements IRole_Service {

    private Role_Repo RoleRepo;
    private ModelMapper mapper;

    @Override
    public Role_DTO addRole(Role_DTO roleDTO) {
        // Map the RoleDTO to the Role entity
        Role role = mapper.map(roleDTO, Role.class);

        // Check if a role with the same ID already exists
//        if (RoleRepo.findById(role.getRoleID()).isPresent()) {
//            throw new DuplicateRoleException("Role with the same ID already exists: " + role.getRoleID());
//        }

        // No role with the same ID, proceed to save the new role
        Role savedRole = RoleRepo.save(role);
        Role_DTO savedRoleDTO = mapper.map(savedRole, Role_DTO.class);
        // Map the saved Role entity back to RoleDTO
        return savedRoleDTO;
    }


    @Override
    public List<Role> getAllRoles() {
        return null;
    }

    @Override
    public Role getRoleByName(String roleName) {
        return null;
    }

    @Override
    public Role updateRole(Integer roleId, Role updatedRole) {
        return null;
    }

    @Override
    public boolean deleteRole(Integer roleId) {
        return false;
    }
}
