package com.example.SkillsetProfiling.Entity;
import com.example.SkillsetProfiling.Key.Test_Attempt_History_Key;
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
@Table(name = "Test_Attempt_History")
@IdClass(Test_Attempt_History_Key.class)
public class Test_Attempt_History {

    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "TestID")
    private Test TestID;
    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "AssessmentID")
    private Assessment AssessmentID;
    private Integer attemptNumber;


}
