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
@Table(name = "Badges")
public class Badges {

    @Id
    private Integer BadgeID;
    private String badge_name;
    @ManyToOne
    @JoinColumn(name = "badgeGroupID")
    private Badge_Groups badge_groups;

}
