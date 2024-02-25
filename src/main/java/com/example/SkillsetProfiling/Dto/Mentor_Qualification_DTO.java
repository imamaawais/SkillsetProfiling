package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Domain;
import com.example.SkillsetProfiling.Entity.Mentor_Details;
import com.example.SkillsetProfiling.Entity.Qualification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mentor_Qualification_DTO {
    private Mentor_Details mentorDetails;
    private Qualification qualification;
    private Domain domain;
    private Boolean completed;
}
