package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Mentor_Details_DTO;

import java.util.List;

public interface IMentor_Details_Service {
    Mentor_Details_DTO addMentorDetails(Mentor_Details_DTO mentorDetailsDTO);
    Mentor_Details_DTO getMentorDetailsByMentorID(Integer mentorID);
    Integer getMentorIDByEmail(String email);
    List<Mentor_Details_DTO> getAllMentorDetails();
    Mentor_Details_DTO updateMentorDetails(Integer mentorID, Mentor_Details_DTO updatedMentorDetailsDTO);
    boolean deleteMentorDetails(Integer mentorID);
}
