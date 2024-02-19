package com.example.SkillsetProfiling.Dto;
import com.example.SkillsetProfiling.Entity.Badge_Groups;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Badges_DTO {
    private Integer BadgeID;
    private String badge_name;
    private Badge_Groups badge_groups;
}
