package com.example.SkillsetProfiling.Dto;
import com.example.SkillsetProfiling.Entity.Question_Difficulty;
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
public class Question_Bank_DTO {
    private Integer QuestionID;
    private String question_text;
    private String answer;
    private Question_Difficulty question_difficulty;
    private Timestamp timestamp_created;
    private Timestamp timestamp_updated;
}
