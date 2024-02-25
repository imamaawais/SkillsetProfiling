package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Auth_DTO;
import com.example.SkillsetProfiling.Dto.Skill_Groups_DTO;
import com.example.SkillsetProfiling.Service.Implementation.Skill_Groups_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/skill_groups")
public class SkillGroupsController {

    private Skill_Groups_Service service;

    @PostMapping
    public ResponseEntity<Skill_Groups_DTO> addSkillGroup(@RequestBody Skill_Groups_DTO skillGroupsDTO){
        Skill_Groups_DTO savedSkillGroup = service.addSkillGroup(skillGroupsDTO);
        return new ResponseEntity<>(savedSkillGroup, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Skill_Groups_DTO>> getAllSkillGroups() {
        List<Skill_Groups_DTO> skillGroups = service.getAllSkillGroups();

        if (skillGroups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(skillGroups, HttpStatus.OK);
    }
}
