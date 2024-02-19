package com.example.SkillsetProfiling.Dto;
import com.example.SkillsetProfiling.Entity.Skill_Level;
import com.example.SkillsetProfiling.Entity.Skills;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question_Paper_DTO {
    private Integer QuestionPaperID;
    private Skill_Level skill_level;
    private Skills skillID;
    private Timestamp timestamp_created;
    private Timestamp timestamp_updated;

}
