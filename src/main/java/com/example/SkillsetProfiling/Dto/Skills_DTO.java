package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Skill_Groups;
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
    private String skillName;
    private Skill_Groups skillGroup;
    private String skillDescription;

}
