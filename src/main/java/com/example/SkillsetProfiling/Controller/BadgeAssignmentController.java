package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Badge_Assignment_DTO;
import com.example.SkillsetProfiling.Exception.BadgeAssignmentNotFoundException;
import com.example.SkillsetProfiling.Exception.DuplicateBadgeAssignmentException;
import com.example.SkillsetProfiling.Service.Implementation.Badge_Assignment_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/badge_assignment")
public class BadgeAssignmentController {

    private final Badge_Assignment_Service service;

    @PostMapping("/add")
    public ResponseEntity<Badge_Assignment_DTO> addBadgeAssignment(@RequestBody Badge_Assignment_DTO badgeAssignmentDTO) {
        try {
            Badge_Assignment_DTO savedBadgeAssignment = service.addBadgeAssignment(badgeAssignmentDTO);
            return new ResponseEntity<>(savedBadgeAssignment, HttpStatus.CREATED);
        } catch (DuplicateBadgeAssignmentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Badge_Assignment_DTO>> getAllBadgeAssignments() {
        List<Badge_Assignment_DTO> badgeAssignments = service.getAllBadgeAssignments();

        if (badgeAssignments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(badgeAssignments, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{badgeId}/{studentId}/{skillId}")
    public ResponseEntity<Badge_Assignment_DTO> getBadgeAssignmentByIds(@PathVariable Integer badgeId, @PathVariable Integer studentId, @PathVariable Integer skillId) {
        try {
            Badge_Assignment_DTO badgeAssignmentDTO = service.getBadgeAssignmentByIds(badgeId, studentId, skillId);
            return new ResponseEntity<>(badgeAssignmentDTO, HttpStatus.OK);
        } catch (BadgeAssignmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{badgeId}/{studentId}/{skillId}")
    public ResponseEntity<Badge_Assignment_DTO> updateBadgeAssignment(@PathVariable Integer badgeId, @PathVariable Integer studentId, @PathVariable Integer skillId, @RequestBody Badge_Assignment_DTO updatedBadgeAssignmentDTO) {
        try {
            Badge_Assignment_DTO updatedBadgeAssignment = service.updateBadgeAssignment(badgeId, studentId, skillId, updatedBadgeAssignmentDTO);
            return new ResponseEntity<>(updatedBadgeAssignment, HttpStatus.OK);
        } catch (BadgeAssignmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{badgeId}/{studentId}/{skillId}")
    public ResponseEntity<Void> deleteBadgeAssignment(@PathVariable Integer badgeId, @PathVariable Integer studentId, @PathVariable Integer skillId) {
        try {
            boolean deleted = service.deleteBadgeAssignment(badgeId, studentId, skillId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (BadgeAssignmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
