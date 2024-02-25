package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Mentor_Details;
import com.example.SkillsetProfiling.Entity.Skills;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mentor_Specialization_DTO {
    private Mentor_Details mentorDetails;
    private Skills skills;
}

