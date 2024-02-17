package com.example.SkillsetProfiling.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Integer skillGroupID;
    private String skill_description;

}
