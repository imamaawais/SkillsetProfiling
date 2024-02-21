package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Domain_DTO;
import com.example.SkillsetProfiling.Entity.Domain;
import com.example.SkillsetProfiling.Exception.DomainNotFoundException;
import com.example.SkillsetProfiling.Exception.DuplicateDomainException;
import com.example.SkillsetProfiling.Repository.Domain_Repo;
import com.example.SkillsetProfiling.Service.Interface.IDomain_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Domain_Service implements IDomain_Service {

    private Domain_Repo DomainRepo;
    private ModelMapper mapper;
    @Override
    public Domain_DTO addDomain(Domain_DTO domainDTO) {
        // Map the DomainDTO to the Domain entity
        Domain domain = mapper.map(domainDTO, Domain.class);

        // Check if a domain with the same ID already exists
        if (DomainRepo.findById(domain.getDomainID()).isPresent()) {
            throw new DuplicateDomainException("Domain with the same ID already exists: " + domain.getDomainID());
        }

        // No domain with the same ID, proceed to save the new domain
        Domain savedDomain = DomainRepo.save(domain);
        Domain_DTO savedDomainDTO = mapper.map(savedDomain, Domain_DTO.class);
        // Map the saved Domain entity back to DomainDTO
        return savedDomainDTO;
    }

    @Override
    public List<Domain_DTO> getAllDomains() {
        List<Domain> domains = DomainRepo.findAll();
        return domains.stream()
                .map(domain -> mapper.map(domain, Domain_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Domain_DTO getDomainByDomainID(Integer domainID)  {
        Optional<Domain> domainOptional = DomainRepo.findById(domainID);

        if (domainOptional.isPresent()) {
            return mapper.map(domainOptional.get(), Domain_DTO.class);
        } else {
            throw new DomainNotFoundException("Domain not found with ID: " + domainID);
        }
    }


    @Override
    public Domain_DTO getDomainByDomainName(String domainName) {
        Optional<Domain> domainOptional = DomainRepo.findByDomainName(domainName);

        if (domainOptional.isPresent()) {
            return mapper.map(domainOptional.get(), Domain_DTO.class);
        } else {
            throw new DomainNotFoundException("Domain not found with name: " + domainName);
        }
    }

    @Override
    public Domain_DTO updateDomain(Integer domainID, Domain_DTO updatedDomainDTO) throws DomainNotFoundException {
        Optional<Domain> domainOptional = DomainRepo.findById(domainID);

        if (domainOptional.isPresent()) {
            Domain existingDomain = domainOptional.get();

            // Update the properties of the existing domain with the values from the updatedDomainDTO
            existingDomain.setDomainName(updatedDomainDTO.getDomainName());
            // Add other properties as needed

            // Save the updated domain
            Domain updatedDomain = DomainRepo.save(existingDomain);

            // Map the updated domain to Domain_DTO and return
            return mapper.map(updatedDomain, Domain_DTO.class);
        } else {
            throw new DomainNotFoundException("Domain not found with ID: " + domainID);
        }
    }


    @Override
    @Transactional
    public boolean deleteDomain(Integer domainID) throws DomainNotFoundException {
        Optional<Domain> domainOptional = DomainRepo.findById(domainID);

        if (domainOptional.isPresent()) {
            Domain domainToDelete = domainOptional.get();
            DomainRepo.delete(domainToDelete);
            return true; // Deletion successful
        } else {
            throw new DomainNotFoundException("Domain not found with ID: " + domainID);
        }
    }
}
