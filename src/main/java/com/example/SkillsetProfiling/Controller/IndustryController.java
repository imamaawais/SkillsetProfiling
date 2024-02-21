package com.example.SkillsetProfiling.Controller;


import com.example.SkillsetProfiling.Dto.Industry_DTO;
import com.example.SkillsetProfiling.Service.Implementation.IndustryNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Industry_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/industry")
public class IndustryController {

    private Industry_Service service;

    @PostMapping
    public ResponseEntity<Industry_DTO> addIndustry(@RequestBody Industry_DTO industryDTO) {
        Industry_DTO addedIndustry = service.addIndustry(industryDTO);
        return new ResponseEntity<>(addedIndustry, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Industry_DTO>> getAllIndustries() {
        List<Industry_DTO> industries = service.getAllIndustries();

        if (industries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(industries, HttpStatus.OK);
    }

    @GetMapping("/id/{industryID}")
    public ResponseEntity<Industry_DTO> getIndustryByID(@PathVariable Integer industryID) throws IndustryNotFoundException {
        Industry_DTO industryDTO = service.getIndustryByID(industryID);
        return new ResponseEntity<>(industryDTO, HttpStatus.OK);
    }

    @GetMapping("/name/{industryName}")
    public ResponseEntity<Industry_DTO> getIndustryByName(@PathVariable String industryName) {
        try {
            Industry_DTO industryDTO = service.getIndustryByName(industryName);
            return new ResponseEntity<>(industryDTO, HttpStatus.OK);
        } catch (IndustryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{industryID}")
    public ResponseEntity<Industry_DTO> updateIndustry(@PathVariable Integer industryID, @RequestBody Industry_DTO updatedIndustryDTO) {
        try {
            Industry_DTO updatedIndustry = service.updateIndustry(industryID, updatedIndustryDTO);
            return new ResponseEntity<>(updatedIndustry, HttpStatus.OK);
        } catch (IndustryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{industryID}")
    public ResponseEntity<Void> deleteIndustry(@PathVariable Integer industryID) {
        try {
            boolean deleted = service.deleteIndustry(industryID);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // Handle case where deletion was not successful
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IndustryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
