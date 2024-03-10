package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Auth_DTO;
import com.example.SkillsetProfiling.Exception.UserNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Auth_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/auth")
public class AuthController {


    private Auth_Service service;

    @PostMapping("/register")
    public ResponseEntity<Auth_DTO> registerUser(@RequestBody Auth_DTO authDTO) {
        Auth_DTO savedAuth = service.addAuth(authDTO);
        return new ResponseEntity<>(savedAuth, HttpStatus.CREATED);
    }



    @GetMapping("/getAll")
    public ResponseEntity<List<Auth_DTO>> getAllUsers() {
        List<Auth_DTO> userList = service.getAllUsers();

        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{email}")
    public ResponseEntity<Auth_DTO> getUserByEmail(@PathVariable String email) {
        Auth_DTO user = service.getUserByEmail(email);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<Auth_DTO> updateUser(@PathVariable String email, @RequestBody Auth_DTO updatedAuthDTO) {
        Auth_DTO updatedAuth = service.updateUser(email, updatedAuthDTO);

        if (updatedAuth == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedAuth, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
       try{
           boolean deleted = service.deleteUser(email);
           if (deleted){
               return new ResponseEntity<>(HttpStatus.OK);
           }else{
               return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
           }
       } catch(UserNotFoundException e){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

    }
}
