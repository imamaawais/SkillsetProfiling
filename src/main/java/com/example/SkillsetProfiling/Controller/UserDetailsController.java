package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.User_Details_DTO;
import com.example.SkillsetProfiling.Exception.UserDetailsNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.User_Details_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/user_details")
public class UserDetailsController {

    private User_Details_Service service;


    @PostMapping("/add")
    public ResponseEntity<User_Details_DTO> addUserDetails(@RequestBody User_Details_DTO userDetailsDTO) {
        User_Details_DTO addedUserDetails = service.addUserDetails(userDetailsDTO);
        return new ResponseEntity<>(addedUserDetails, HttpStatus.CREATED);
    }

    @GetMapping("/getFromId/{userDetailsID}")
    public ResponseEntity<User_Details_DTO> getUserDetailsByUserID(@PathVariable Integer userDetailsID) {
        User_Details_DTO userDetailsDTO = service.getUserDetailsByUserID(userDetailsID);

        if (userDetailsDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDetailsDTO, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User_Details_DTO>> getAllUserDetails() {
        List<User_Details_DTO> userDetailsList = service.getAllUserDetails();

        if (userDetailsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(userDetailsList, HttpStatus.OK);
    }

    @PutMapping("/update/{userID}")
    public ResponseEntity<User_Details_DTO> updateUserDetails(@PathVariable Integer userID,
                                                              @RequestBody User_Details_DTO updatedUserDetailsDTO) {
        try {
            User_Details_DTO updatedUserDetails = service.updateUserDetails(userID, updatedUserDetailsDTO);
            return new ResponseEntity<>(updatedUserDetails, HttpStatus.OK);
        } catch (UserDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<Void> deleteUserDetails(@PathVariable Integer userID) {
        try {
            boolean deleted = service.deleteUserDetails(userID);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (UserDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


}}
