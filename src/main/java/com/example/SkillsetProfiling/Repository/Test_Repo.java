package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Test_Repo extends JpaRepository<Test, Integer> {
    @Query("SELECT t FROM Test t WHERE t.studentSkillLevel.studentDetails.StudentID = :StudentID AND t.studentSkillLevel.skills.SkillID = :SkillID AND t.studentSkillLevel.skillLevel.LevelID = :LevelID")
    List<Test> findTestByStudentSkillLevel(@Param("StudentID") Integer StudentID, @Param("SkillID") Integer SkillID, @Param("LevelID") Integer LevelID);
}
