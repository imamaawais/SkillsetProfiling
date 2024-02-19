package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Auth_DTO;
import com.example.SkillsetProfiling.Service.Implementation.Auth_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private Auth_Service service;

//    @PostMapping
//    public ResponseEntity<Auth_DTO> addTodo(@RequestBody Auth_DTO AuthDTO){
//
//        Auth_DTO savedAuth = service.addAuth(AuthDTO);
//
//        return new ResponseEntity<>(savedAuth, HttpStatus.CREATED);
//    }

    @PostMapping("/register")
    public ResponseEntity<Auth_DTO> registerUser(@RequestBody Auth_DTO authDTO) {
        Auth_DTO savedAuth = service.addAuth(authDTO);
        return new ResponseEntity<>(savedAuth, HttpStatus.CREATED);
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "API is working!";
    }

    @GetMapping("/users")
    public ResponseEntity<List<Auth_DTO>> getAllUsers() {
        List<Auth_DTO> userList = service.getAllUsers(); // Assuming you have a method in your service to fetch all users

        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


}
