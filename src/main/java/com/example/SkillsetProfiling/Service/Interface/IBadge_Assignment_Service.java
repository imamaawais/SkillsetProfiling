package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Badge_Assignment_DTO;

import java.util.List;

public interface IBadge_Assignment_Service {
    Badge_Assignment_DTO addBadgeAssignment(Badge_Assignment_DTO badgeAssignmentDTO);

    Badge_Assignment_DTO getBadgeAssignmentByIds(Integer badgeId, Integer studentId, Integer skillId);

    List<Badge_Assignment_DTO> getAllBadgeAssignments();

    Badge_Assignment_DTO updateBadgeAssignment(Integer badgeId, Integer studentId, Integer skillId, Badge_Assignment_DTO updatedBadgeAssignmentDTO);

    boolean deleteBadgeAssignment(Integer badgeId, Integer studentId, Integer skillId);
}
