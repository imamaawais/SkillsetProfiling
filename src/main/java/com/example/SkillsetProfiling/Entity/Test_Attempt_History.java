package com.example.SkillsetProfiling.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Test_Attempt_History")
public class Test_Attempt_History {

    @Id
    private Integer TestID;
    private Integer AssessmentID;
    private Integer attempt_number;


}
