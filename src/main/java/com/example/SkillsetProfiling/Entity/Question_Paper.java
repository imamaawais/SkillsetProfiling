package com.example.SkillsetProfiling.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Question_Paper")
public class Question_Paper {

    @Id
    private Integer QuestionPaperID;
    private Integer levelID;
    private Integer skillID;
    private Timestamp timestamp_created;
    private Timestamp timestamp_updated;

}
