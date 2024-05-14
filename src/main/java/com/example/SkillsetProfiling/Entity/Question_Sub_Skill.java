package com.example.SkillsetProfiling.Entity;
import com.example.SkillsetProfiling.Key.Question_Sub_Skill_Key;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "QuestionID")
    private Question_Bank QuestionID;
    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "SubSkillID")
    // Inconsistent variable name cz was getting error with name "SubSkillID" -> Also changed in Question_Sub_Skill_Key
    private Sub_Skills subSkills;

}
