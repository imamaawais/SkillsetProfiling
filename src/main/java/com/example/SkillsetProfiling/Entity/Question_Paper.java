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
@Table(name = "Question_Paper")
public class Question_Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer QuestionPaperID;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "levelID")
    private Skill_Level skillLevel;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "skillID")
    private Skills skillID;
    private Timestamp timestampCreated;
    private Timestamp timestampUpdated;

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
