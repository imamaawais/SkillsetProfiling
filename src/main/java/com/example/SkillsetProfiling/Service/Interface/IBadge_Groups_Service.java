package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Badge_Groups_DTO;
import com.example.SkillsetProfiling.Exception.BadgeGroupNotFoundException;

import java.util.List;

public interface IBadge_Groups_Service {

    Badge_Groups_DTO addBadgeGroup(Badge_Groups_DTO badgeGroupsDTO);
    Badge_Groups_DTO getBadgeGroupById(Integer BadgeGroupID) throws BadgeGroupNotFoundException;
    List<Badge_Groups_DTO> getAllBadgeGroups();
    Badge_Groups_DTO updateBadgeGroup(Integer BadgeGroupID, Badge_Groups_DTO updatedBadgeGroupsDTO);

    boolean deleteBadgeGroup(Integer BadgeGroupID);
}
