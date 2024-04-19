package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Question_Bank;
import com.example.SkillsetProfiling.Entity.Question_Paper;
import com.example.SkillsetProfiling.Entity.Question_Sub_Skill;
import com.example.SkillsetProfiling.Key.Question_Sub_Skill_Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Question_Sub_Skill_Repo extends JpaRepository<Question_Sub_Skill, Question_Sub_Skill_Key> {
    @Query("SELECT qs.QuestionID FROM Question_Sub_Skill qs WHERE qs.subSkills.skills.SkillID = :SkillID AND qs.QuestionID.questionDifficulty.DifficultyID = :DifficultyID")
    List<Question_Bank> findQuestionBySkillAndDifficulty(@Param("SkillID") Integer SkillID, @Param("DifficultyID") Integer DifficultyID);
}
