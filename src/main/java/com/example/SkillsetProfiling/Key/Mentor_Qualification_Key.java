package com.example.SkillsetProfiling.Key;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mentor_Qualification_Key implements Serializable {
    private Integer MentorID;
    private Integer QualificationID;
    private Integer DomainID;
}
