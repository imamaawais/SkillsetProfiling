package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Skills_DTO;
import com.example.SkillsetProfiling.Exception.SkillNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Skills_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/skills")
public class SkillsController {

    private Skills_Service service;

    @PostMapping("/add")
    public ResponseEntity<Skills_DTO> addSkill(@RequestBody Skills_DTO skillsDTO) {
        Skills_DTO savedSkill = service.addSkill(skillsDTO);
        return new ResponseEntity<>(savedSkill, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Skills_DTO>> getAllSkills() {
        List<Skills_DTO> skills = service.getAllSkills();

        if (skills.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{SkillId}")
    public ResponseEntity<Skills_DTO> getSkillGroupById(@PathVariable int SkillId) throws SkillNotFoundException {
        Skills_DTO skillsDTO = service.getSkillById(SkillId);

        return new ResponseEntity<>(skillsDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{SkillId}")
    public ResponseEntity<Skills_DTO> updateSkill(@PathVariable Integer SkillId, @RequestBody Skills_DTO updatedSkillDTO) {
        try {
            Skills_DTO updatedSkill = service.updateSkill(SkillId, updatedSkillDTO);
            return new ResponseEntity<>(updatedSkill, HttpStatus.OK);
        } catch (SkillNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{SkillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Integer SkillId) {
        try {
            boolean deleted = service.deleteSkill(SkillId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (SkillNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
