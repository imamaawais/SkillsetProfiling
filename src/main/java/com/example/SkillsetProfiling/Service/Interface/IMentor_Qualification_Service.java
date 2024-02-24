package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Mentor_Qualification_DTO;
import com.example.SkillsetProfiling.Key.Mentor_Qualification_Key;

import java.util.List;
import java.util.Optional;

public interface IMentor_Qualification_Service {
    List<Mentor_Qualification_DTO> getAllMentorQualifications();

    Optional<Mentor_Qualification_DTO> getMentorQualificationById(Mentor_Qualification_Key key);

    Mentor_Qualification_DTO saveMentorQualification(Mentor_Qualification_DTO mentorQualificationDTO);

    void deleteMentorQualification(Mentor_Qualification_Key key);

}
