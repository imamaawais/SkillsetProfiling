package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Skill_Level_DTO;
import com.example.SkillsetProfiling.Exception.SkillLevelNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Skill_Level_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/skill_level")
public class SkillLevelController {

    private Skill_Level_Service service;

    @PostMapping("/add")
    public ResponseEntity<Skill_Level_DTO> addSkillLevel(@RequestBody Skill_Level_DTO skillLevelDTO) {
        Skill_Level_DTO savedSkillLevel = service.addSkillLevel(skillLevelDTO);
        return new ResponseEntity<>(savedSkillLevel, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Skill_Level_DTO>> getAllSkillLevels() {
        List<Skill_Level_DTO> skillLevel = service.getAllSkillLevels();

        if (skillLevel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(skillLevel, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{SkillLevelId}")
    public ResponseEntity<Skill_Level_DTO> getSkillLevelById(@PathVariable int SkillLevelId) throws SkillLevelNotFoundException {
        Skill_Level_DTO skillLevelDTO = service.getSkillLevelById(SkillLevelId);

        return new ResponseEntity<>(skillLevelDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{SkillLevelId}")
    public ResponseEntity<Skill_Level_DTO> updateSkillLevel(@PathVariable Integer SkillLevelId, @RequestBody Skill_Level_DTO updatedSkillLevelDTO) {
        try {
            Skill_Level_DTO updatedSkillLevel = service.updateSkillLevel(SkillLevelId, updatedSkillLevelDTO);
            return new ResponseEntity<>(updatedSkillLevel, HttpStatus.OK);
        } catch (SkillLevelNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{SkillLevelId}")
    public ResponseEntity<Void> deleteSkillLevel(@PathVariable Integer SkillLevelId) {
        try {
            boolean deleted = service.deleteSkillLevel(SkillLevelId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (SkillLevelNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
