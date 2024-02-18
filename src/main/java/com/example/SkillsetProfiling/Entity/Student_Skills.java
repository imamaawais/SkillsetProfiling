package com.example.SkillsetProfiling.Entity;

import com.example.SkillsetProfiling.Key.Student_Skills_Key;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Student_Skills")
@IdClass(Student_Skills_Key.class)
public class Student_Skills {
    @Id
    @ManyToOne
    @JoinColumn(name = "StudentID")
    private Student_Details student_details;
    @Id
    @ManyToOne
    @JoinColumn(name = "SkillID")
    private Skills skills;
    private Integer self_level;
    private Integer levelID;
    private Timestamp timestamp_updated;

}
