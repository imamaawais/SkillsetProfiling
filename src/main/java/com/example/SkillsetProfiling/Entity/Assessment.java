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
@Table(name = "Assessment")
public class Assessment {

    @Id
    private Integer AssessmentID;
    private Integer questionPaperID;
    private Integer studentID;
    private Integer time_taken;
    private Integer total_score;
    private Timestamp assessment_timestamp;
    private Boolean is_passed;


}
