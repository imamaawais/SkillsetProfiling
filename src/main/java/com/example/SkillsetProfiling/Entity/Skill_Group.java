package com.example.SkillsetProfiling.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Skill_Group")
public class Skill_Group {
    @Id
    private Integer SkillGroupID;
    private String group_name;
    private String group_description;
}