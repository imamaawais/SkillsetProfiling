package com.example.SkillsetProfiling.Entity;
import com.example.SkillsetProfiling.Key.Assessment_Scores_Key;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "AssessmentID")
    private Assessment AssessmentID;
    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "QuestionID")
    private Question_Bank QuestionID;
    private String userAnswer;
    private Boolean isAttempted;
}
