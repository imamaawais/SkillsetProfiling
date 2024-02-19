package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Skills;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sub_Skills_DTO {
    private Integer SubSkillID;
    private String sub_skill_name;
    private String sub_skill_description;
    private Skills skills;

}
