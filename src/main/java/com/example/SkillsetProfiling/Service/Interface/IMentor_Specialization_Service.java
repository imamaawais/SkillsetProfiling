package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Mentor_Specialization_DTO;

import java.util.List;

public interface IMentor_Specialization_Service {
    Mentor_Specialization_DTO addMentorSpecialization(Mentor_Specialization_DTO mentorSpecializationDTO);

    Mentor_Specialization_DTO getMentorSpecializationByIds(Integer mentorId, Integer skillId);

    List<Mentor_Specialization_DTO> getAllMentorSpecializations();

    Mentor_Specialization_DTO updateMentorSpecialization(Integer mentorId, Integer skillId, Mentor_Specialization_DTO updatedMentorSpecializationDTO);

    boolean deleteMentorSpecialization(Integer mentorId, Integer skillId);
}
