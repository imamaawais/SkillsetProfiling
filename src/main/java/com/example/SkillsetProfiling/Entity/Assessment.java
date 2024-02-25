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
@Table(name = "Assessment")
public class Assessment {
    @Id
    private Integer AssessmentID;
    @ManyToOne
    @JoinColumn(name = "question_PaperID")
    private Question_Paper questionPaper;
    @ManyToOne
    @JoinColumn(name = "studentID")
    private Student_Details studentDetails;
    private Integer timeTaken;
    private Integer totalScore;
    private Timestamp assessmentTimestamp;
    private Boolean isPassed;
}
