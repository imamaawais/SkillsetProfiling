package com.example.SkillsetProfiling.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Question_Bank")
public class Question_Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer QuestionID;
    private String questionText;
    private String answer;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "difficultyID")
    private Question_Difficulty questionDifficulty;
    private Timestamp timestampCreated;
    private Timestamp timestampUpdated;
    boolean isMCQ;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    @PrePersist
    protected void onCreate() {
        timestampCreated = new Timestamp(System.currentTimeMillis());
        timestampUpdated = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        timestampUpdated = new Timestamp(System.currentTimeMillis());
    }
}
