package com.example.SkillsetProfiling.Entity;

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
@Table(name = "Student_Details")
public class Student_Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer StudentID;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userID")
    private User_Details userDetails;
    private Integer enrollmentYear;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "domainID")
    private Domain domain;
    private Integer expectedGraduation;
    @Lob
    private byte[] resume;
}
