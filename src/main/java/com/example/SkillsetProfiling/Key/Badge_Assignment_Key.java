package com.example.SkillsetProfiling.Key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Badge_Assignment_Key implements Serializable {
    private Integer StudentID;
    private Integer SkillID;
    private Integer BadgeID;
}
