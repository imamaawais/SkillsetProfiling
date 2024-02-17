package com.example.SkillsetProfiling.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
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
@Table(name = "Badge_Groups")
public class Badge_Groups {

    @Id
    private Integer BadgeGroupID;
    private String badge_group_name;
    private String badge_group_description;
}
