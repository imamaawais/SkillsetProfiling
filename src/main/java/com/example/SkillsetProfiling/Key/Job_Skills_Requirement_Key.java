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
public class Job_Skills_Requirement_Key implements Serializable {
    private Integer JobID;
    private Integer SkillID;
    private Integer LevelID;
}
