package com.example.SkillsetProfiling.Entity;

import com.example.SkillsetProfiling.Key.Job_Skills_Requirements_Key;
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
@Table(name = "Job_Skills_Requirements")
@IdClass(Job_Skills_Requirements_Key.class)
public class Job_Skills_Requirements {

    @Id
    @ManyToOne
    @JoinColumn(name = "JobID")
    private Job_Postings JobID;
    @Id
    @ManyToOne
    @JoinColumn(name = "SkillID")
    private Skills SkillID;
    @ManyToOne
    @JoinColumn(name = "LevelID")
    private Skill_Level LevelID;
}
