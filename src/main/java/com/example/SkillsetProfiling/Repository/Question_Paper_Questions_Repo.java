package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Question_Paper_Questions;
import com.example.SkillsetProfiling.Key.Question_Paper_Questions_Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Question_Paper_Questions_Repo extends JpaRepository<Question_Paper_Questions, Question_Paper_Questions_Key> {
}
