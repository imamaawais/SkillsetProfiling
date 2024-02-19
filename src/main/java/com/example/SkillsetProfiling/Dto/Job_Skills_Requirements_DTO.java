package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Job_Postings;
import com.example.SkillsetProfiling.Entity.Skill_Level;
import com.example.SkillsetProfiling.Entity.Skills;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job_Skills_Requirements_DTO {
    private Job_Postings job_postings;
    private Skills skills;
    private Skill_Level skill_level;
}

