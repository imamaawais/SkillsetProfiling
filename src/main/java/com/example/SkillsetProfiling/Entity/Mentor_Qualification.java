package com.example.SkillsetProfiling.Entity;

import com.example.SkillsetProfiling.Key.Mentor_Qualification_Key;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MentorID")
    private Mentor_Details MentorID;
    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "QualificationID")
    private Qualification QualificationID;
    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "DomainID")
    private Domain DomainID;
    private Boolean completed;
}
