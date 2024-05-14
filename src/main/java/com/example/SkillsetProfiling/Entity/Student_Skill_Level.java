package com.example.SkillsetProfiling.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Student_Skill_Level")
public class Student_Skill_Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer StudentSkillLevelID;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "studentID")
    private Student_Details studentDetails;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "skillID")
    private Skills skills;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "levelID")
    private Skill_Level skillLevel;
    private Integer progress;
    private Timestamp timestampUpdated;

    @PrePersist
    protected void onCreate() {
        timestampUpdated = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        timestampUpdated = new Timestamp(System.currentTimeMillis());
    }
}
