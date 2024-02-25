package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Mentor_Qualification_DTO;
import com.example.SkillsetProfiling.Key.Mentor_Qualification_Key;

import java.util.List;

public interface IMentor_Qualification_Service {
    Mentor_Qualification_DTO addMentorQualification(Mentor_Qualification_DTO mentorQualificationDTO);
    List<Mentor_Qualification_DTO> getAllMentorQualifications();
    Mentor_Qualification_DTO getMentorQualificationById(Mentor_Qualification_Key key);
    Mentor_Qualification_DTO updateMentorQualification(Mentor_Qualification_Key key, Mentor_Qualification_DTO updatedMentorQualificationDTO);
    boolean deleteMentorQualification(Mentor_Qualification_Key key);

}
