package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Assessment;
import com.example.SkillsetProfiling.Entity.Student_Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Assessment_Repo extends JpaRepository<Assessment, Integer> {
    @Query("SELECT a FROM Assessment a WHERE a.studentDetails.StudentID = :StudentID")
    Optional<List<Assessment>> findAllByStudentID_StudentID(@Param("StudentID") Integer StudentID);

}
