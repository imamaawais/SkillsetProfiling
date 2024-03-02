package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Badges_DTO;
import com.example.SkillsetProfiling.Entity.Badges;
import com.example.SkillsetProfiling.Exception.BadgeNotFoundException;
import com.example.SkillsetProfiling.Exception.DuplicateBadgeException;
import com.example.SkillsetProfiling.Repository.Badges_Repo;
import com.example.SkillsetProfiling.Service.Interface.IBadges_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Badges_Service implements IBadges_Service {

    private Badges_Repo badgesRepo;
    private ModelMapper mapper;

    @Override
    public Badges_DTO addBadge(Badges_DTO badgesDTO) {

        Badges badge = mapper.map(badgesDTO, Badges.class);

        if(badgesRepo.findById(badge.getBadgeID()).isPresent()){
            throw new DuplicateBadgeException("Badge with the same Id already exists: "+ badge.getBadgeID());
        }

        //save to db
        Badges savedBadge = badgesRepo.save(badge);
        Badges_DTO savedBadgeDTO = mapper.map(savedBadge, Badges_DTO.class);

        return savedBadgeDTO;
    }

    @Override
    public Badges_DTO getBadgeById(Integer BadgeID) throws BadgeNotFoundException {
        Optional<Badges> badgesOptional = badgesRepo.findById(BadgeID);

        if (badgesOptional.isPresent()) {
            return mapper.map(badgesOptional.get(), Badges_DTO.class);
        } else {
            throw new BadgeNotFoundException("Badge not found with id: " + BadgeID);
        }
    }

    @Override
    public List<Badges_DTO> getAllBadges() {
        List<Badges> badges = badgesRepo.findAll();

        System.out.println(badges);
        return badges.stream()
                .map(badge-> mapper.map(badge, Badges_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Badges_DTO updateBadge(Integer BadgeID, Badges_DTO updatedBadgesDTO) {
        Optional<Badges> badgesOptional = badgesRepo.findById(BadgeID);

        if (badgesOptional.isPresent()) {
            Badges existingBadge = badgesOptional.get();

            existingBadge.setBadgeName(updatedBadgesDTO.getBadgeName());
            existingBadge.setBadgeGroups(updatedBadgesDTO.getBadgeGroups());

            Badges updatedBadge = badgesRepo.save(existingBadge);

            return mapper.map(updatedBadge, Badges_DTO.class);
        } else {
            throw new BadgeNotFoundException("Badge not found with ID: " + BadgeID);
        }
    }

    @Override
    @Transactional
    public boolean deleteBadge(Integer BadgeID) {
        Optional<Badges> badgesOptional = badgesRepo.findById(BadgeID);

        if (badgesOptional.isPresent()) {
            Badges badgeToDelete = badgesOptional.get();
            badgesRepo.delete(badgeToDelete);
            return true; // Deletion successful
        } else {
            throw new BadgeNotFoundException("Badge not found with ID: " + BadgeID);
        }
    }
}

