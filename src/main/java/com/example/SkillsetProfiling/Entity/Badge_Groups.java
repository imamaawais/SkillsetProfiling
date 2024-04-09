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
@Table(name = "Badge_Groups")
public class Badge_Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer BadgeGroupID;
    private String badgeGroupName;
    private String badgeGroupDescription;
}
