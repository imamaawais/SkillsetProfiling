package com.example.SkillsetProfiling.Entity;
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
@Entity
@Table(name = "Question_Options")
public class Question_Options {
    @Id
    @ManyToOne
    @JoinColumn(name = "QuestionID")
    Question_Bank QuestionID;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
}
