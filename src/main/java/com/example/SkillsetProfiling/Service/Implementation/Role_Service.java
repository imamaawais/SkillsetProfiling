package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Role_DTO;
import com.example.SkillsetProfiling.Entity.Role;
import com.example.SkillsetProfiling.Exception.DuplicateRoleException;
import com.example.SkillsetProfiling.Repository.Role_Repo;
import com.example.SkillsetProfiling.Service.Interface.IRole_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Role_Service implements IRole_Service {

    private Role_Repo roleRepo;
    private ModelMapper mapper;

    @Override
    public Role_DTO addRole(Role_DTO roleDTO) {
        // Map the RoleDTO to the Role entity
        Role role = mapper.map(roleDTO, Role.class);

        // Check if a role with the same ID already exists
        if (roleRepo.findById(role.getRoleID()).isPresent()) {
            throw new DuplicateRoleException("Role with the same ID already exists: " + role.getRoleID());
        }

        // No role with the same ID, proceed to save the new role
        Role savedRole = roleRepo.save(role);
        Role_DTO savedRoleDTO = mapper.map(savedRole, Role_DTO.class);
        // Map the saved Role entity back to RoleDTO
        return savedRoleDTO;
    }


    @Override
    public List<Role_DTO> getAllRoles() {
        List<Role> roles = roleRepo.findAll();
        return roles.stream()
                .map(role -> mapper.map(role, Role_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Role_DTO getRoleByRoleID(Integer roleId) throws RoleNotFoundException {
        Optional<Role> roleOptional = roleRepo.findById(roleId);

        if (roleOptional.isPresent()) {
            return mapper.map(roleOptional.get(), Role_DTO.class);
        } else {
            throw new RoleNotFoundException("Role not found with ID: " + roleId);
        }

    }

    @Override
    public Role_DTO getRoleByRoleName(String role_name) throws RoleNotFoundException {
        Optional<Role> roleOptional = roleRepo.findByRoleName(role_name);

        if (roleOptional.isPresent()) {
            return mapper.map(roleOptional.get(), Role_DTO.class);
        } else {
            throw new RoleNotFoundException("Role not found with name: " + role_name);
        }
    }


    @Override
    public Role_DTO updateRole(Integer roleId, Role_DTO updatedRoleDTO) throws RoleNotFoundException{
    Optional<Role> roleOptional = roleRepo.findById(roleId);

        if (roleOptional.isPresent()) {
            Role existingRole = roleOptional.get();

            // Update the properties of the existing role with the values from the updatedRoleDTO
            existingRole.setRoleName(updatedRoleDTO.getRoleName());
            // Add other properties as needed

            // Save the updated role
            Role updatedRole = roleRepo.save(existingRole);

            // Map the updated role to Role_DTO and return
            return mapper.map(updatedRole, Role_DTO.class);
        } else {
            throw new RoleNotFoundException("Role not found with ID: " + roleId);
        }
    }

    @Override
    @Transactional
    public boolean deleteRole(Integer roleId) throws RoleNotFoundException {
        Optional<Role> roleOptional = roleRepo.findById(roleId);

        if (roleOptional.isPresent()) {
            Role roleToDelete = roleOptional.get();
            roleRepo.delete(roleToDelete);
            return true; // Deletion successful
        } else {
            throw new RoleNotFoundException("Role not found with ID: " + roleId);
        }
    }

}
