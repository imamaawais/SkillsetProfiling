package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Sub_Skills_DTO;
import com.example.SkillsetProfiling.Exception.SubSkillNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Sub_Skills_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/sub_skills")
public class SubSkillsController {
    private Sub_Skills_Service service;

    @PostMapping("/add")
    public ResponseEntity<Sub_Skills_DTO> addSubSkill(@RequestBody Sub_Skills_DTO subSkillsDTO) {
        Sub_Skills_DTO savedSubSkill = service.addSubSkill(subSkillsDTO);
        return new ResponseEntity<>(savedSubSkill, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Sub_Skills_DTO>> getAllSubSkills() {
        List<Sub_Skills_DTO> subSkills = service.getAllSubSkills();

        if (subSkills.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(subSkills, HttpStatus.OK);
    }

    @GetMapping("/getFromSkillId/{skillID}")
    public ResponseEntity<List<Sub_Skills_DTO>> getSubSkillsBySkillID(@PathVariable int skillID) {
        List<Sub_Skills_DTO> subSkills = service.getSubSkillsBySkillID(skillID);

        if (subSkills.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(subSkills, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{subSkillId}")
    public ResponseEntity<Sub_Skills_DTO> getSubSkillById(@PathVariable int subSkillId) throws SubSkillNotFoundException {
        Sub_Skills_DTO subSkillsDTO = service.getSubSkillById(subSkillId);

        return new ResponseEntity<>(subSkillsDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{subSkillId}")
    public ResponseEntity<Sub_Skills_DTO> updateSubSkill(@PathVariable Integer subSkillId, @RequestBody Sub_Skills_DTO updatedSubSkillDTO) {
        try {
            Sub_Skills_DTO updatedSubSkill = service.updateSubSkill(subSkillId, updatedSubSkillDTO);
            return new ResponseEntity<>(updatedSubSkill, HttpStatus.OK);
        } catch (SubSkillNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{subSkillId}")
    public ResponseEntity<Void> deleteSubSkill(@PathVariable Integer subSkillId) {
        try {
            boolean deleted = service.deleteSubSkill(subSkillId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (SubSkillNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
