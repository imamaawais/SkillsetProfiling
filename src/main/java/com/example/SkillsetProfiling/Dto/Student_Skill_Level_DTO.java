package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Skill_Level;
import com.example.SkillsetProfiling.Entity.Skills;
import com.example.SkillsetProfiling.Entity.Student_Details;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student_Skill_Level_DTO {
    private Integer StudentSkillLevelID;
    private Student_Details studentDetails;
    private Skills skills;
    private Skill_Level skillLevel;
    private Integer progress;
    private Timestamp timestampUpdated;
}
