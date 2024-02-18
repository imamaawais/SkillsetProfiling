package com.example.SkillsetProfiling.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Student_Skill_Level")
public class Student_Skill_Level {
    @Id
    private Integer StudentSkillLevelID;
    @ManyToOne
    @JoinColumn(name = "studentID")
    private Student_Details student_details;
    @ManyToOne
    @JoinColumn(name = "skillID")
    private Skills skills;
    @ManyToOne
    @JoinColumn(name = "levelID")
    private Skill_Level skill_level;
    private Integer progress;
    private Timestamp timestamp_updated;
}
