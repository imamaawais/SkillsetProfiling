package com.example.SkillsetProfiling.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Skill_Level_DTO {
    private Integer LevelID;
    private String levelName;
    private Integer noTestsRequired;
}
