package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Skills;
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
    private String subSkillName;
    private String subSkillDescription;
    private Skills skills;

}
