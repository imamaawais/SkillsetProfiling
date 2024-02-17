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
@Table(name = "Mentor_Qualification")
public class Mentor_Qualification {
    @Id
    private Integer MentorID;
    @Id
    private Integer QualificationID;
    @Id
    private Integer DomainID;
    private Boolean completed;
}
