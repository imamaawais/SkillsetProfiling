package com.example.SkillsetProfiling.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "Skills")
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer SkillID;
    private String skillName;
    @ManyToOne
    @JoinColumn(name = "skill_GroupID")
    private Skill_Groups skillGroup;
    private String skillDescription;

}
