package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Skill_Level;
import com.example.SkillsetProfiling.Entity.Skills;
import com.example.SkillsetProfiling.Entity.Student_Details;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student_Skills_DTO {
    private Student_Details studentDetails;
    private Skills skill;
    private Integer selfLevel;
    private Skill_Level skillLevel;
    private Timestamp timestampUpdated;

}