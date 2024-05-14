package com.example.SkillsetProfiling.Entity;
import com.example.SkillsetProfiling.Key.Student_Experience_Key;
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
@Table(name = "Student_Experience")
@IdClass(Student_Experience_Key.class)
public class Student_Experience {

    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "StudentID")
    private Student_Details StudentID;
    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ExperienceID")
    private Experience ExperienceID;
    private Integer noOfYears;
}
