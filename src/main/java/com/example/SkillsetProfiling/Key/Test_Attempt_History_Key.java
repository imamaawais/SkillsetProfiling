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
public class Test_Attempt_History_Key implements Serializable {
    private Integer TestID;
    private Integer AssessmentID;
}
