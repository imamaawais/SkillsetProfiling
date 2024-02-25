package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.HR_Details_DTO;
import com.example.SkillsetProfiling.Exception.HRDetailsNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.HR_Details_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/hr_details")
public class HRDetailsController {
    private HR_Details_Service service;

    @PostMapping("/add")
    public ResponseEntity<HR_Details_DTO> addHRDetails(@RequestBody HR_Details_DTO hrDetailsDTO) {
        HR_Details_DTO addedHRDetails = service.addHRDetails(hrDetailsDTO);
        return new ResponseEntity<>(addedHRDetails, HttpStatus.CREATED);
    }

    @GetMapping("/getFromId/{hrDetailsID}")
    public ResponseEntity<HR_Details_DTO> getHRDetailsByHRID(@PathVariable Integer hrDetailsID) {
        HR_Details_DTO hrDetailsDTO = service.getHRDetailsByHRID(hrDetailsID);

        if (hrDetailsDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(hrDetailsDTO, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<HR_Details_DTO>> getAllHRDetails() {
        List<HR_Details_DTO> hrDetailsList = service.getAllHRDetails();

        if (hrDetailsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(hrDetailsList, HttpStatus.OK);
    }

    @PutMapping("/update/{hrID}")
    public ResponseEntity<HR_Details_DTO> updateHRDetails(@PathVariable Integer hrID,
                                                              @RequestBody HR_Details_DTO updatedHRDetailsDTO) {
        try {
            HR_Details_DTO updatedHRDetails = service.updateHRDetails(hrID, updatedHRDetailsDTO);
            return new ResponseEntity<>(updatedHRDetails, HttpStatus.OK);
        } catch (HRDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/delete/{hrID}")
    public ResponseEntity<Void> deleteHRDetails(@PathVariable Integer hrID) {
        try {
            boolean deleted = service.deleteHRDetails(hrID);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (HRDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
