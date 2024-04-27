package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Student_Skill_Level;
import com.example.SkillsetProfiling.Entity.Student_Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Student_Skill_Level_Repo extends JpaRepository<Student_Skill_Level, Integer> {
    @Query("SELECT ssl FROM Student_Skill_Level ssl WHERE ssl.studentDetails.StudentID = :StudentID AND ssl.skills.SkillID = :SkillID AND ssl.skillLevel.LevelID = :LevelID")
    Optional<Student_Skill_Level> findByStudentSkillLevel(@Param("StudentID") Integer StudentID, @Param("SkillID") Integer SkillID, @Param("LevelID") Integer LevelID);
}
