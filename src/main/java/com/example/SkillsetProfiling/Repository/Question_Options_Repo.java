package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Question_Bank;
import com.example.SkillsetProfiling.Entity.Question_Options;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Question_Options_Repo extends JpaRepository<Question_Options, Integer> {
}
