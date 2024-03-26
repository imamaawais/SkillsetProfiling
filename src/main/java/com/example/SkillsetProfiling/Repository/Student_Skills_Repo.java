package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Student_Skills;
import com.example.SkillsetProfiling.Key.Student_Skills_Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Student_Skills_Repo extends JpaRepository<Student_Skills, Student_Skills_Key> {
    @Query("SELECT ss FROM Student_Skills ss WHERE ss.StudentID.StudentID = :StudentID")
    Optional<List<Student_Skills>> findAllByStudentID_StudentID(@Param("StudentID") Integer StudentID);
}
