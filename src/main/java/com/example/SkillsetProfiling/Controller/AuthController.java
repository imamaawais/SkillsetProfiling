package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Auth_DTO;
import com.example.SkillsetProfiling.Service.Implementation.Auth_Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/auth")
public class AuthController {

    private Auth_Service service;

    @PostMapping
    public ResponseEntity<Auth_DTO> addTodo(@RequestBody Auth_DTO AuthDTO){

        Auth_DTO savedAuth = service.addAuth(AuthDTO);

        return new ResponseEntity<>(savedAuth, HttpStatus.CREATED);
    }

}
