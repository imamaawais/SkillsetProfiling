package com.example.SkillsetProfiling.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Question_Bank")
public class Question_Bank {

    @Id
    private Integer QuestionID;
    private String question_text;
    private String answer;
    private Integer difficultyID;
    private Timestamp timestamp_created;
    private Timestamp timestamp_updated;
}
