package com.example.SkillsetProfiling.Entity;

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
@Table(name = "Skill_Groups")
public class Skill_Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer SkillGroupID;
    private String groupName;
    private String groupDescription;
}
