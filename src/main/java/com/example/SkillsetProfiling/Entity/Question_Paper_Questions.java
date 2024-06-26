package com.example.SkillsetProfiling.Entity;
import com.example.SkillsetProfiling.Key.Question_Paper_Questions_Key;
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
@Table(name = "Question_Paper_Questions")
@IdClass(Question_Paper_Questions_Key.class)
public class Question_Paper_Questions {

    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "QuestionPaperID")
    private Question_Paper QuestionPaperID;
    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "QuestionID")
    private Question_Bank QuestionID;

}
