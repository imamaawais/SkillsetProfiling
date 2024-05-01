package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Assessment_Scores;
import com.example.SkillsetProfiling.Key.Assessment_Scores_Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Assessment_Scores_Repo extends JpaRepository<Assessment_Scores, Assessment_Scores_Key> {
    @Query("SELECT ascore FROM Assessment_Scores ascore WHERE ascore.AssessmentID.AssessmentID = :AssessmentID")
    List<Assessment_Scores> findAllByAssessmentID(@Param("AssessmentID") Integer AssessmentID);
}
