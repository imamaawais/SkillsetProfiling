package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Student_Skills;
import com.example.SkillsetProfiling.Key.Student_Skills_Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Student_Skills_Repo extends JpaRepository<Student_Skills, Student_Skills_Key> {
}
