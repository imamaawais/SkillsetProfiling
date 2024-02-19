package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Skill_Groups;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Skills_DTO {
    private Integer SkillID;
    private String skill_name;
    private Skill_Groups skill_group;
    private String skill_description;

}