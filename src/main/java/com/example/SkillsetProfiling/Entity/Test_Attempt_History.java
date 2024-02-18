package com.example.SkillsetProfiling.Entity;
import com.example.SkillsetProfiling.Key.Test_Attempt_History_Key;
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
@Table(name = "Test_Attempt_History")
@IdClass(Test_Attempt_History_Key.class)
public class Test_Attempt_History {

    @Id
    @ManyToOne
    @JoinColumn(name = "TestID")
    private Test test;
    @Id
    @ManyToOne
    @JoinColumn(name = "AssessmentID")
    private Assessment assessment;
    private Integer attempt_number;


}
