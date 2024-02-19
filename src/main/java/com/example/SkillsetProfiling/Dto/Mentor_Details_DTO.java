package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.User_Details;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mentor_Details_DTO {
    private Integer MentorID;
    private User_Details user_details;
}