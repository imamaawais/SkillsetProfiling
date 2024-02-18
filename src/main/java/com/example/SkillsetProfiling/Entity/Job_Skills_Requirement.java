package com.example.SkillsetProfiling.Entity;

import com.example.SkillsetProfiling.Key.Job_Skills_Requirement_Key;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Job_Skills_Requirement")
@IdClass(Job_Skills_Requirement_Key.class)
public class Job_Skills_Requirement {

    @Id
    @ManyToOne
    @JoinColumn(name = "JobID")
    private Job_Postings job_postings;
    @Id
    @ManyToOne
    @JoinColumn(name = "SkillID")
    private Skills skills;
    @Id
    @ManyToOne
    @JoinColumn(name = "LevelID")
    private Skill_Level skill_level;
}
