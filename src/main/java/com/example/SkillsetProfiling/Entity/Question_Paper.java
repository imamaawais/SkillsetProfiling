package com.example.SkillsetProfiling.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Question_Paper")
public class Question_Paper {

    @Id
    private Integer QuestionPaperID;
    @ManyToOne
    @JoinColumn(name = "levelID")
    private Skill_Level skill_level;
    @ManyToOne
    @JoinColumn(name = "skillID")
    private Skills skillID;
    private Timestamp timestamp_created;
    private Timestamp timestamp_updated;

}
