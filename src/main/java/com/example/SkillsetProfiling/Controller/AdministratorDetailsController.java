package com.example.SkillsetProfiling.Controller;


import com.example.SkillsetProfiling.Dto.Administrator_Details_DTO;
import com.example.SkillsetProfiling.Exception.AdministratorDetailsNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Administrator_Details_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/administrator_details")
public class AdministratorDetailsController {
    private Administrator_Details_Service service;



    @PostMapping("/add")
    public ResponseEntity<Administrator_Details_DTO> addAdministratorDetails(@RequestBody Administrator_Details_DTO administratorDetailsDTO) {
        Administrator_Details_DTO addedAdministratorDetails = service.addAdministratorDetails(administratorDetailsDTO);
        return new ResponseEntity<>(addedAdministratorDetails, HttpStatus.CREATED);
    }

    @GetMapping("/getFromId/{administratorID}")
    public ResponseEntity<Administrator_Details_DTO> getAdministratorDetailsByAdministratorID(@PathVariable Integer administratorID) {
        try {
            Administrator_Details_DTO administratorDetailsDTO = service.getAdministratorDetailsByAdministratorID(administratorID);
            return new ResponseEntity<>(administratorDetailsDTO, HttpStatus.OK);
        } catch (AdministratorDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Administrator_Details_DTO>> getAllAdministratorDetails() {
        List<Administrator_Details_DTO> administratorDetailsList = service.getAllAdministratorDetails();

        if (administratorDetailsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(administratorDetailsList, HttpStatus.OK);
    }

    @PutMapping("/update/{administratorID}")
    public ResponseEntity<Administrator_Details_DTO> updateAdministratorDetails(@PathVariable Integer administratorID,
                                                                                @RequestBody Administrator_Details_DTO updatedAdministratorDetailsDTO) {
        try {
            Administrator_Details_DTO updatedAdministratorDetails = service.updateAdministratorDetails(administratorID, updatedAdministratorDetailsDTO);
            return new ResponseEntity<>(updatedAdministratorDetails, HttpStatus.OK);
        } catch (AdministratorDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{administratorID}")
    public ResponseEntity<Void> deleteAdministratorDetails(@PathVariable Integer administratorID) {
        try {
            boolean deleted = service.deleteAdministratorDetails(administratorID);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (AdministratorDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
