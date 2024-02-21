package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Domain_DTO;
import com.example.SkillsetProfiling.Exception.DomainNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Domain_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/domains")
public class DomainController {
    private Domain_Service service;

    @PostMapping
    public ResponseEntity<Domain_DTO> addDomain(@RequestBody Domain_DTO domainDTO) {
        Domain_DTO addedDomain = service.addDomain(domainDTO);
        return new ResponseEntity<>(addedDomain, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Domain_DTO>> getAllDomains() {
        List<Domain_DTO> domains = service.getAllDomains();

        if (domains.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(domains, HttpStatus.OK);
    }

    @GetMapping("/id/{domainID}")
    public ResponseEntity<Domain_DTO> getDomainByDomainID(@PathVariable Integer domainID) throws DomainNotFoundException {
        Domain_DTO domainDTO = service.getDomainByDomainID(domainID);
        return new ResponseEntity<>(domainDTO, HttpStatus.OK);
    }

    @GetMapping("/name/{domainName}")
    public ResponseEntity<Domain_DTO> getDomainByDomainName(@PathVariable String domainName) {
        try {
            Domain_DTO domainDTO = service.getDomainByDomainName(domainName);
            return new ResponseEntity<>(domainDTO, HttpStatus.OK);
        } catch (DomainNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{domainID}")
    public ResponseEntity<Domain_DTO> updateDomain(@PathVariable Integer domainID, @RequestBody Domain_DTO updatedDomainDTO) {
        try {
            Domain_DTO updatedDomain = service.updateDomain(domainID, updatedDomainDTO);
            return new ResponseEntity<>(updatedDomain, HttpStatus.OK);
        } catch (DomainNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{domainID}")
    public ResponseEntity<Void> deleteDomain(@PathVariable Integer domainID) {
        try {
            boolean deleted = service.deleteDomain(domainID);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // Handle case where deletion was not successful
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (DomainNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
