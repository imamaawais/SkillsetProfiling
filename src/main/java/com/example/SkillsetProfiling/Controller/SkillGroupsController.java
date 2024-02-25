package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Auth_DTO;
import com.example.SkillsetProfiling.Dto.Role_DTO;
import com.example.SkillsetProfiling.Dto.Skill_Groups_DTO;
import com.example.SkillsetProfiling.Exception.SkillGroupNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Skill_Groups_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/skill_groups")
public class SkillGroupsController {

    private Skill_Groups_Service service;

    @PostMapping
    public ResponseEntity<Skill_Groups_DTO> addSkillGroup(@RequestBody Skill_Groups_DTO skillGroupsDTO) {
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

    @GetMapping("/id/{SkillGroupId}")
    public ResponseEntity<Skill_Groups_DTO> getSkillGroupById(@PathVariable int SkillGroupId) throws SkillGroupNotFoundException {
        Skill_Groups_DTO skillGroupsDTO = service.getSkillGroupById(SkillGroupId);

        return new ResponseEntity<>(skillGroupsDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{SkillGroupId}")
    public ResponseEntity<Skill_Groups_DTO> updateSkillGroup(@PathVariable Integer SkillGroupId, @RequestBody Skill_Groups_DTO updatedSkillGroupsDTO) {
        try {
            Skill_Groups_DTO updatedSkillGroup = service.updateSkillGroup(SkillGroupId, updatedSkillGroupsDTO);
            return new ResponseEntity<>(updatedSkillGroup, HttpStatus.OK);
        } catch (SkillGroupNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{SkillGroupId}")
    public ResponseEntity<Void> deleteSkillGroup(@PathVariable Integer SkillGroupId) {
        try {
            boolean deleted = service.deleteSkillGroup(SkillGroupId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (SkillGroupNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
