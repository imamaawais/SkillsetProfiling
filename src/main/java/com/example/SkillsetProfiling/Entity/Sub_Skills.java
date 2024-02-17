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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "Sub_Skills")
public class Sub_Skills {

    @Id
    private Integer SubSkillID;
    private String sub_skill_name;
    private String sub_skill_description;
    private Integer skillID;

}
