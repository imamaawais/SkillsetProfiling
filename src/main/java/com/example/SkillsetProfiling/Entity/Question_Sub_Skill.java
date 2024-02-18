package com.example.SkillsetProfiling.Entity;
import com.example.SkillsetProfiling.Key.Question_Sub_Skill_Key;
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
@Table(name = "Question_Sub_Skill")
@IdClass(Question_Sub_Skill_Key.class)
public class Question_Sub_Skill {

    @Id
    @ManyToOne
    @JoinColumn(name = "QuestionID")
    private Question_Bank question_bank;
    @Id
    @ManyToOne
    @JoinColumn(name = "SubSkillID")
    private Sub_Skills sub_skills;

}
