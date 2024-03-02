package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Assessment_Scores;
import com.example.SkillsetProfiling.Key.Assessment_Scores_Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Assessment_Scores_Repo extends JpaRepository<Assessment_Scores, Assessment_Scores_Key> {

}
