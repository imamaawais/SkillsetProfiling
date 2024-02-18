package com.example.SkillsetProfiling.Entity;

import com.example.SkillsetProfiling.Key.Mentor_Specialization_Key;
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
@Table(name = "Mentor_Specialization")
@IdClass(Mentor_Specialization_Key.class)
public class Mentor_Specialization {

    @Id
    @ManyToOne
    @JoinColumn(name = "MentorID")
    private Mentor_Details mentor_details;
    @Id
    @ManyToOne
    @JoinColumn(name = "SkillID")
    private Skills skills;

}
