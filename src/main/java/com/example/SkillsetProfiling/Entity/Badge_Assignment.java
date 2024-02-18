package com.example.SkillsetProfiling.Entity;
import com.example.SkillsetProfiling.Key.Badge_Assignment_Key;
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
@Table(name = "Badge_Assignment")
@IdClass(Badge_Assignment_Key.class)
public class Badge_Assignment {
    @Id
    @ManyToOne
    @JoinColumn(name = "BadgeID")
    private Badges badges;
    @Id
    @ManyToOne
    @JoinColumn(name = "StudentID")
    private Student_Details student_details;
    @Id
    @ManyToOne
    @JoinColumn(name = "SkillID")
    private Skills skills;
}
