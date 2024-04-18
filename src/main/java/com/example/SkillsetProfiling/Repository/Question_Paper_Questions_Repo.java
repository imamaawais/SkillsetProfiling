package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Question_Paper_Questions;
import com.example.SkillsetProfiling.Entity.Student_Skills;
import com.example.SkillsetProfiling.Key.Question_Paper_Questions_Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Question_Paper_Questions_Repo extends JpaRepository<Question_Paper_Questions, Question_Paper_Questions_Key> {
    @Query("SELECT qpq FROM Question_Paper_Questions qpq WHERE qpq.QuestionPaperID.QuestionPaperID  = :QuestionID")
    List<Question_Paper_Questions> findByQuestionPaperID(@Param("QuestionID") Integer QuestionID);
}
