package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Question_Paper;
import com.example.SkillsetProfiling.Entity.Student_Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Question_Paper_Repo extends JpaRepository<Question_Paper, Integer> {
    @Query("SELECT qp FROM Question_Paper qp WHERE qp.skillID.SkillID = :SkillID AND qp.skillLevel.LevelID = :SkillLevelID")
    List<Question_Paper> findAllBySkillIDAndSkillLevel(@Param("SkillID") Integer SkillID, @Param("SkillLevelID") Integer SkillLevelID);
}
