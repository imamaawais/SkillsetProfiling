package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Badge_Groups_DTO;
import com.example.SkillsetProfiling.Entity.Badge_Groups;
import com.example.SkillsetProfiling.Exception.BadgeGroupNotFoundException;
import com.example.SkillsetProfiling.Exception.DuplicateBadgeGroupException;
import com.example.SkillsetProfiling.Repository.Badge_Groups_Repo;
import com.example.SkillsetProfiling.Service.Interface.IBadge_Groups_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class Badge_Groups_Service implements IBadge_Groups_Service {

    private Badge_Groups_Repo badgeGroupsRepo;
    private ModelMapper mapper;

    @Override
    public Badge_Groups_DTO addBadgeGroup(Badge_Groups_DTO badgeGroupsDTO) {

        Badge_Groups badgeGroup = mapper.map(badgeGroupsDTO, Badge_Groups.class);

        if(badgeGroupsRepo.findById(badgeGroup.getBadgeGroupID()).isPresent()){
            throw new DuplicateBadgeGroupException("Badge Group with the same Id already exists: "+ badgeGroup.getBadgeGroupID());
        }

        //save to db
        Badge_Groups savedBadgeGroup = badgeGroupsRepo.save(badgeGroup);
        Badge_Groups_DTO savedBadgeGroupDTO = mapper.map(savedBadgeGroup, Badge_Groups_DTO.class);

        return savedBadgeGroupDTO;
    }

    @Override
    public Badge_Groups_DTO getBadgeGroupById(Integer BadgeGroupID) throws BadgeGroupNotFoundException {
        Optional<Badge_Groups> badgeGroupsOptional = badgeGroupsRepo.findById(BadgeGroupID);

        if (badgeGroupsOptional.isPresent()) {
            return mapper.map(badgeGroupsOptional.get(), Badge_Groups_DTO.class);
        } else {
            throw new BadgeGroupNotFoundException("Badge Group not found with id: " + BadgeGroupID);
        }
    }

    @Override
    public List<Badge_Groups_DTO> getAllBadgeGroups() {
        List<Badge_Groups> badgeGroups = badgeGroupsRepo.findAll();

        System.out.println(badgeGroups);
        return badgeGroups.stream()
                .map(badgeGroup-> mapper.map(badgeGroup, Badge_Groups_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Badge_Groups_DTO updateBadgeGroup(Integer BadgeGroupID, Badge_Groups_DTO updatedBadgeGroupsDTO) {
        Optional<Badge_Groups> badgeGroupsOptional = badgeGroupsRepo.findById(BadgeGroupID);

        if (badgeGroupsOptional.isPresent()) {
            Badge_Groups existingBadgeGroup = badgeGroupsOptional.get();

            existingBadgeGroup.setBadgeGroupName(updatedBadgeGroupsDTO.getBadgeGroupName());
            existingBadgeGroup.setBadgeGroupDescription(updatedBadgeGroupsDTO.getBadgeGroupDescription());

            Badge_Groups updatedBadgeGroup = badgeGroupsRepo.save(existingBadgeGroup);

            return mapper.map(updatedBadgeGroup, Badge_Groups_DTO.class);
        } else {
            throw new BadgeGroupNotFoundException("Badge Group not found with ID: " + BadgeGroupID);
        }
    }

    @Override
    @Transactional
    public boolean deleteBadgeGroup(Integer BadgeGroupID) {
        Optional<Badge_Groups> badgeGroupsOptional = badgeGroupsRepo.findById(BadgeGroupID);

        if (badgeGroupsOptional.isPresent()) {
            Badge_Groups badgeGroupToDelete = badgeGroupsOptional.get();
            badgeGroupsRepo.delete(badgeGroupToDelete);
            return true; // Deletion successful
        } else {
            throw new BadgeGroupNotFoundException("Badge Group not found with ID: " + BadgeGroupID);
        }
    }
}
