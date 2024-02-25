package com.example.SkillsetProfiling.Dto;
import com.example.SkillsetProfiling.Entity.Question_Bank;
import com.example.SkillsetProfiling.Entity.Question_Paper;
import com.example.SkillsetProfiling.Key.Question_Paper_Questions_Key;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question_Paper_Questions_DTO {
    private Question_Paper questionPaper;
    private Question_Bank questionBank;

}
