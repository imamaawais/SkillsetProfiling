package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Badges;
import com.example.SkillsetProfiling.Entity.Skills;
import com.example.SkillsetProfiling.Entity.Student_Details;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Badge_Assignment_DTO {
    private Badges badges;
    private Student_Details studentDetails;
    private Skills skills;
}
