package com.example.SkillsetProfiling.Controller;


import com.example.SkillsetProfiling.Dto.Role_DTO;
import com.example.SkillsetProfiling.Service.Implementation.Role_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/role")
public class RoleController {

    private Role_Service service;

    @PostMapping
    public ResponseEntity<Role_DTO> addRole(@RequestBody Role_DTO roleDTO) {
        Role_DTO addedRole = service.addRole(roleDTO);
        return new ResponseEntity<>(addedRole, HttpStatus.CREATED);
    }



}
