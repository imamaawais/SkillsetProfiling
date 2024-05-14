package com.example.SkillsetProfiling.Entity;

import com.example.SkillsetProfiling.Key.Student_Skills_Key;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "StudentID")
    private Student_Details StudentID;
    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "SkillID")
    private Skills SkillID;
    private Integer selfLevel;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="LevelID")
    private Skill_Level LevelID;
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
