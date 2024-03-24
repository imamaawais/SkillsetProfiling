package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Badge_Assignment_DTO;
import com.example.SkillsetProfiling.Entity.Badge_Assignment;
import com.example.SkillsetProfiling.Exception.BadgeAssignmentNotFoundException;
import com.example.SkillsetProfiling.Exception.DuplicateBadgeAssignmentException;
import com.example.SkillsetProfiling.Key.Badge_Assignment_Key;
import com.example.SkillsetProfiling.Repository.Badge_Assignment_Repo;
import com.example.SkillsetProfiling.Service.Interface.IBadge_Assignment_Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Badge_Assignment_Service implements IBadge_Assignment_Service {

    private final Badge_Assignment_Repo badgeAssignmentRepo;
//    private final ModelMapper modelMapper;

    @Override
    public Badge_Assignment_DTO addBadgeAssignment(Badge_Assignment_DTO badgeAssignmentDTO) {
        Badge_Assignment badgeAssignment = new Badge_Assignment(badgeAssignmentDTO.getBadges(),badgeAssignmentDTO.getStudentDetails(),badgeAssignmentDTO.getSkills());
        if (badgeAssignmentRepo.findById(new Badge_Assignment_Key(badgeAssignment.getStudentID().getStudentID(), badgeAssignment.getSkillID().getSkillID(), badgeAssignment.getBadgeID().getBadgeID())).isPresent()) {
            throw new DuplicateBadgeAssignmentException("Badge assignment already exists with IDs: " + badgeAssignment.getBadgeID().getBadgeID() + ", " + badgeAssignment.getStudentID().getStudentID() + ", " + badgeAssignment.getSkillID().getSkillID());
        }
        Badge_Assignment savedBadgeAssignment = badgeAssignmentRepo.save(badgeAssignment);
        return new Badge_Assignment_DTO(savedBadgeAssignment.getBadgeID(),savedBadgeAssignment.getStudentID(),savedBadgeAssignment.getSkillID());
    }

    @Override
    public Badge_Assignment_DTO getBadgeAssignmentByIds(Integer badgeId, Integer studentId, Integer skillId) throws BadgeAssignmentNotFoundException {
        Optional<Badge_Assignment> badgeAssignmentOptional = badgeAssignmentRepo.findById(new Badge_Assignment_Key(studentId, skillId, badgeId));
        if (badgeAssignmentOptional.isPresent()) {
            return new Badge_Assignment_DTO(badgeAssignmentOptional.get().getBadgeID(),badgeAssignmentOptional.get().getStudentID(),badgeAssignmentOptional.get().getSkillID());

        } else {
            throw new BadgeAssignmentNotFoundException("Badge assignment not found with IDs: " + badgeId + ", " + studentId + ", " + skillId);
        }
    }

    @Override
    public List<Badge_Assignment_DTO> getAllBadgeAssignments() {
        List<Badge_Assignment> badgeAssignments = badgeAssignmentRepo.findAll();
        return badgeAssignments.stream()
                .map(assignment -> new Badge_Assignment_DTO(assignment.getBadgeID(),assignment.getStudentID(),assignment.getSkillID()))
                .collect(Collectors.toList());
    }

    @Override
    public Badge_Assignment_DTO updateBadgeAssignment(Integer badgeId, Integer studentId, Integer skillId, Badge_Assignment_DTO updatedBadgeAssignmentDTO) throws BadgeAssignmentNotFoundException {
        Optional<Badge_Assignment> badgeAssignmentOptional = badgeAssignmentRepo.findById(new Badge_Assignment_Key(studentId, skillId, badgeId));
        if (badgeAssignmentOptional.isPresent()) {
            Badge_Assignment existingBadgeAssignment = badgeAssignmentOptional.get();

            Badge_Assignment updatedBadgeAssignment = badgeAssignmentRepo.save(existingBadgeAssignment);
            return new Badge_Assignment_DTO(updatedBadgeAssignment.getBadgeID(),updatedBadgeAssignment.getStudentID(),updatedBadgeAssignment.getSkillID());
        } else {
            throw new BadgeAssignmentNotFoundException("Badge assignment not found with IDs: " + badgeId + ", " + studentId + ", " + skillId);
        }
    }

    @Override
    public boolean deleteBadgeAssignment(Integer badgeId, Integer studentId, Integer skillId) throws BadgeAssignmentNotFoundException {
        Optional<Badge_Assignment> badgeAssignmentOptional = badgeAssignmentRepo.findById(new Badge_Assignment_Key(studentId, skillId, badgeId));
        if (badgeAssignmentOptional.isPresent()) {
            badgeAssignmentRepo.delete(badgeAssignmentOptional.get());
            return true;
        } else {
            throw new BadgeAssignmentNotFoundException("Badge assignment not found with IDs: " + badgeId + ", " + studentId + ", " + skillId);
        }
    }
}
