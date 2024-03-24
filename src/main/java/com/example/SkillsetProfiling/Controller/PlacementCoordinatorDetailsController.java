package com.example.SkillsetProfiling.Controller;


import com.example.SkillsetProfiling.Dto.Placement_Coordinator_Details_DTO;
import com.example.SkillsetProfiling.Exception.CoordinatorDetailsNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Placement_Coordinator_Details_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/placement_coordinator_details")
public class PlacementCoordinatorDetailsController {
    private Placement_Coordinator_Details_Service service;

    @PostMapping("/add")
    public ResponseEntity<Placement_Coordinator_Details_DTO> addCoordinatorDetails(@RequestBody Placement_Coordinator_Details_DTO coordinatorDetailsDTO) {
        Placement_Coordinator_Details_DTO addedCoordinatorDetails = service.addCoordinatorDetails(coordinatorDetailsDTO);
        return new ResponseEntity<>(addedCoordinatorDetails, HttpStatus.CREATED);
    }

    @GetMapping("/getFromId/{coordinatorID}")
    public ResponseEntity<Placement_Coordinator_Details_DTO> getCoordinatorDetailsByCoordinatorID(@PathVariable Integer coordinatorID) {
        try {
            Placement_Coordinator_Details_DTO coordinatorDetailsDTO = service.getCoordinatorDetailsByCoordinatorID(coordinatorID);
            return new ResponseEntity<>(coordinatorDetailsDTO, HttpStatus.OK);
        } catch (CoordinatorDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getFromEmail/{email}")
    public ResponseEntity<Integer> getCoordinatorIDByEmail(@PathVariable String email) {
        try {
            Integer id = service.getCoordinatorIDByEmail(email);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (CoordinatorDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Placement_Coordinator_Details_DTO>> getAllCoordinatorDetails() {
        List<Placement_Coordinator_Details_DTO> coordinatorDetailsList = service.getAllCoordinatorDetails();

        if (coordinatorDetailsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(coordinatorDetailsList, HttpStatus.OK);
    }

    @PutMapping("/update/{coordinatorID}")
    public ResponseEntity<Placement_Coordinator_Details_DTO> updateCoordinatorDetails(@PathVariable Integer coordinatorID,
                                                                                      @RequestBody Placement_Coordinator_Details_DTO updatedCoordinatorDetailsDTO) {
        try {
            Placement_Coordinator_Details_DTO updatedCoordinatorDetails = service.updateCoordinatorDetails(coordinatorID, updatedCoordinatorDetailsDTO);
            return new ResponseEntity<>(updatedCoordinatorDetails, HttpStatus.OK);
        } catch (CoordinatorDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{coordinatorID}")
    public ResponseEntity<Void> deleteCoordinatorDetails(@PathVariable Integer coordinatorID) {
        try {
            boolean deleted = service.deleteCoordinatorDetails(coordinatorID);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (CoordinatorDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
