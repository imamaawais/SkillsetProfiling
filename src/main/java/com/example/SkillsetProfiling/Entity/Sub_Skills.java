package com.example.SkillsetProfiling.Entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer SubSkillID;
    private String subSkillName;
    private String subSkillDescription;
    @ManyToOne
    @JoinColumn(name = "skillID")
    private Skills skills;

}
