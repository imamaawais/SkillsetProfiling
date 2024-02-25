package com.example.SkillsetProfiling.Entity;

import com.example.SkillsetProfiling.Key.Student_Skills_Key;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

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
    private Student_Details StudentID;
    @Id
    @ManyToOne
    @JoinColumn(name = "SkillID")
    private Skills SkillID;
    private Integer selfLevel;
    private Integer levelID;
    private Timestamp timestampUpdated;

    @PrePersist
    protected void onCreate() {
        timestampUpdated = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        timestampUpdated = new Timestamp(System.currentTimeMillis());
    }
}
