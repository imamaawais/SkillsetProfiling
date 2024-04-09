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
@Table(name = "Mentor_Details")
public class Mentor_Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer MentorID;

    @OneToOne
    @JoinColumn(name = "userID")
    private User_Details userDetails;
}
