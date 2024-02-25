package com.example.SkillsetProfiling.Dto;

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
    private Student_Details student_details;
    private Skills skills;
    private Integer selfLevel;
    private Integer levelID;
    private Timestamp timestampUpdated;

}
