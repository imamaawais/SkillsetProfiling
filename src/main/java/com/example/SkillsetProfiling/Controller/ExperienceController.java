package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Experience_DTO;
import com.example.SkillsetProfiling.Service.Implementation.Experience_Service;
import com.example.SkillsetProfiling.Exception.ExperienceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/experience")
public class ExperienceController {

    private Experience_Service service;

    @PostMapping("/add")
    public ResponseEntity<Experience_DTO> addExperience(@RequestBody Experience_DTO experienceDTO) {
        Experience_DTO addedExperience = service.addExperience(experienceDTO);
        return new ResponseEntity<>(addedExperience, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Experience_DTO>> getAllExperiences() {
        List<Experience_DTO> experiences = service.getAllExperiences();

        if(experiences.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(experiences, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{experienceID}")
    public ResponseEntity<Experience_DTO> getExperienceByID(@PathVariable Integer experienceID) throws ExperienceNotFoundException {
        Experience_DTO experienceDTO = service.getExperienceByID(experienceID);

        return new ResponseEntity<>(experienceDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{experienceID}")
    public ResponseEntity<Experience_DTO> updateExperience(@PathVariable Integer experienceID, @RequestBody Experience_DTO updatedExperienceDTO) {
        try {
            Experience_DTO updatedExperience = service.updateExperience(experienceID, updatedExperienceDTO);
            return new ResponseEntity<>(updatedExperience, HttpStatus.OK);
        } catch (ExperienceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{experienceID}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Integer experienceID) {
        try {
            boolean deleted = service.deleteExperience(experienceID);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ExperienceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
