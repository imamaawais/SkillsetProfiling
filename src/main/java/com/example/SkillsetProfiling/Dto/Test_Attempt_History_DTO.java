package com.example.SkillsetProfiling.Dto;
import com.example.SkillsetProfiling.Entity.Assessment;
import com.example.SkillsetProfiling.Entity.Test;
import com.example.SkillsetProfiling.Key.Test_Attempt_History_Key;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Test_Attempt_History_DTO {
    private Test test;
    private Assessment assessment;
    private Integer attemptNumber;


}
