package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Badges_DTO;
import com.example.SkillsetProfiling.Exception.BadgeNotFoundException;

import java.util.List;

public interface IBadges_Service {

    Badges_DTO addBadge(Badges_DTO badgesDTO);
    Badges_DTO getBadgeById(Integer BadgeID) throws BadgeNotFoundException;
    List<Badges_DTO> getAllBadges();
    Badges_DTO updateBadge(Integer BadgeID, Badges_DTO updatedBadgesDTO);

    boolean deleteBadge(Integer BadgeID);
}
