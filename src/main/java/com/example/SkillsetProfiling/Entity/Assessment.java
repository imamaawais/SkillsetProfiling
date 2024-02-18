package com.example.SkillsetProfiling.Entity;
import jakarta.persistence.*;
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
@Table(name = "Assessment")
public class Assessment {

    @Id
    private Integer AssessmentID;
    @ManyToOne
    @JoinColumn(name = "questionPaperID")
    private Question_Paper question_paper;
    @ManyToOne
    @JoinColumn(name = "studentID")
    private Student_Details student_details;
    private Integer time_taken;
    private Integer total_score;
    private Timestamp assessment_timestamp;
    private Boolean is_passed;


}
