package com.example.SkillsetProfiling.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Integer studentID;
    private Integer skillID;
    private Integer levelID;
    private Integer progress;
    private Timestamp timestamp_updated;
}
