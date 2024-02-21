package com.example.SkillsetProfiling.Entity;
import com.example.SkillsetProfiling.Key.Assessment_Scores_Key;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Assessment_Scores")
@IdClass(Assessment_Scores_Key.class)
public class Assessment_Scores {
    @Id
    @ManyToOne
    @JoinColumn(name = "AssessmentID")
    private Assessment AssessmentID;
    @Id
    @ManyToOne
    @JoinColumn(name = "QuestionID")
    private Question_Bank QuestionID;
    private String userAnswer;
    private Boolean isAttempted;
}
