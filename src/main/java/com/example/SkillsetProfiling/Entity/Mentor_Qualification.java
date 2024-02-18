package com.example.SkillsetProfiling.Entity;

import com.example.SkillsetProfiling.Key.Mentor_Qualification_Key;
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
@Table(name = "Mentor_Qualification")
@IdClass(Mentor_Qualification_Key.class)
public class Mentor_Qualification {

    @Id
    @ManyToOne
    @JoinColumn(name = "MentorID")
    private Mentor_Details MentorID;
    @Id
    @ManyToOne
    @JoinColumn(name = "QualificationID")
    private Qualification QualificationID;
    @Id
    @ManyToOne
    @JoinColumn(name = "DomainID")
    private Domain DomainID;
    private Boolean completed;
}
