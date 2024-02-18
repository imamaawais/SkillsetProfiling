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
    private Integer SkillID;
    private String skill_name;
    @ManyToOne
    @JoinColumn(name = "skillGroupID")
    private Skill_Group skill_group;
    private String skill_description;

}
