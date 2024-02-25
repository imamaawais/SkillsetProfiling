package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Badges_DTO;
import com.example.SkillsetProfiling.Exception.BadgeNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Badges_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/badges")
public class BadgesController {

    private Badges_Service service;

    @PostMapping("/add")
    public ResponseEntity<Badges_DTO> addBadge(@RequestBody Badges_DTO badgesDTO) {
        Badges_DTO savedBadge = service.addBadge(badgesDTO);
        return new ResponseEntity<>(savedBadge, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Badges_DTO>> getAllBadges() {
        List<Badges_DTO> badges = service.getAllBadges();

        if (badges.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(badges, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{BadgeId}")
    public ResponseEntity<Badges_DTO> getBadgeById(@PathVariable int BadgeId) throws BadgeNotFoundException {
        Badges_DTO badgesDTO = service.getBadgeById(BadgeId);

        return new ResponseEntity<>(badgesDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{BadgeId}")
    public ResponseEntity<Badges_DTO> updateBadge(@PathVariable Integer BadgeId, @RequestBody Badges_DTO updatedBadgeDTO) {
        try {
            Badges_DTO updatedBadge = service.updateBadge(BadgeId, updatedBadgeDTO);
            return new ResponseEntity<>(updatedBadge, HttpStatus.OK);
        } catch (BadgeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{BadgeId}")
    public ResponseEntity<Void> deleteBadge(@PathVariable Integer BadgeId) {
        try {
            boolean deleted = service.deleteBadge(BadgeId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (BadgeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

