package com.example.SkillsetProfiling.Dto;
import com.example.SkillsetProfiling.Entity.Question_Bank;
import com.example.SkillsetProfiling.Entity.Sub_Skills;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question_Sub_Skill_DTO {
    private Question_Bank questionBank;
    private Sub_Skills subSkills;

}
