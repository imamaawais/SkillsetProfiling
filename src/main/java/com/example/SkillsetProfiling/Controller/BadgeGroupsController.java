package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Badge_Groups_DTO;
import com.example.SkillsetProfiling.Exception.BadgeGroupNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Badge_Groups_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/badge_groups")
public class BadgeGroupsController {

    private Badge_Groups_Service service;

    @PostMapping("/add")
    public ResponseEntity<Badge_Groups_DTO> addBadgeGroup(@RequestBody Badge_Groups_DTO badgeGroupsDTO) {
        Badge_Groups_DTO savedBadgeGroup = service.addBadgeGroup(badgeGroupsDTO);
        return new ResponseEntity<>(savedBadgeGroup, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Badge_Groups_DTO>> getAllBadgeGroups() {
        List<Badge_Groups_DTO> badgeGroups = service.getAllBadgeGroups();

        if (badgeGroups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(badgeGroups, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{BadgeGroupId}")
    public ResponseEntity<Badge_Groups_DTO> getBadgeGroupById(@PathVariable int BadgeGroupId) throws BadgeGroupNotFoundException {
        Badge_Groups_DTO badgeGroupsDTO = service.getBadgeGroupById(BadgeGroupId);

        return new ResponseEntity<>(badgeGroupsDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{BadgeGroupId}")
    public ResponseEntity<Badge_Groups_DTO> updateBadgeGroup(@PathVariable Integer BadgeGroupId, @RequestBody Badge_Groups_DTO updatedBadgeGroupsDTO) {
        try {
            Badge_Groups_DTO updatedBadgeGroup = service.updateBadgeGroup(BadgeGroupId, updatedBadgeGroupsDTO);
            return new ResponseEntity<>(updatedBadgeGroup, HttpStatus.OK);
        } catch (BadgeGroupNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{BadgeGroupId}")
    public ResponseEntity<Void> deleteBadgeGroup(@PathVariable Integer BadgeGroupId) {
        try {
            boolean deleted = service.deleteBadgeGroup(BadgeGroupId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (BadgeGroupNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

