package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Student_Experience;
import com.example.SkillsetProfiling.Key.Student_Experience_Key;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Student_Experience_Repo extends JpaRepository<Student_Experience, Student_Experience_Key> {
    List<Student_Experience> findByStudentID_StudentID(Integer studentID);
    List<Student_Experience> findByExperienceID_ExperienceID(Integer experienceID);
}
