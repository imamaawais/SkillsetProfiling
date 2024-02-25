package com.example.SkillsetProfiling.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Skill_Groups_DTO {
    private Integer SkillGroupID;
    private String groupName;
    private String groupDescription;
}
