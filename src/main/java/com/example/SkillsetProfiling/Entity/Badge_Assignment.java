package com.example.SkillsetProfiling.Entity;
import com.example.SkillsetProfiling.Key.Badge_Assignment_Key;
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
@Table(name = "Badge_Assignment")
@IdClass(Badge_Assignment_Key.class)
public class Badge_Assignment {
    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "BadgeID")
    private Badges BadgeID;
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
}
