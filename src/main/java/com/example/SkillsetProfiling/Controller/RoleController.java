package com.example.SkillsetProfiling.Controller;


import com.example.SkillsetProfiling.Dto.Role_DTO;
import com.example.SkillsetProfiling.Service.Implementation.Role_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/roles")
public class RoleController {

    private Role_Service service;

    @PostMapping
    public ResponseEntity<Role_DTO> addRole(@RequestBody Role_DTO roleDTO) {
        Role_DTO addedRole = service.addRole(roleDTO);
        return new ResponseEntity<>(addedRole, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Role_DTO>> getAllRoles() {
        List<Role_DTO> roles = service.getAllRoles();

        if(roles.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{RoleID}")
    public ResponseEntity<Role_DTO> getRoleByRoleID(@PathVariable int RoleID) throws RoleNotFoundException {
        Role_DTO roleDTO = service.getRoleByRoleID(RoleID);

        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

//    @GetMapping("/{roleName}")
//    public ResponseEntity<Role_DTO> getRoleByRoleName(@PathVariable String roleName) {
//        try {
//            Role_DTO roleDTO = service.getRoleByRoleName(roleName);
//            return new ResponseEntity<>(roleDTO, HttpStatus.OK);
//        } catch (RoleNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }


    @PutMapping("/{RoleId}")
    public ResponseEntity<Role_DTO> updateRole(@PathVariable Integer RoleId, @RequestBody Role_DTO updatedRoleDTO) {
        try {
            Role_DTO updatedRole = service.updateRole(RoleId, updatedRoleDTO);
            return new ResponseEntity<>(updatedRole, HttpStatus.OK);
        } catch (RoleNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{RoleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer RoleId) {
        try {
            boolean deleted = service.deleteRole(RoleId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // Handle case where deletion was not successful
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (RoleNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }









}
