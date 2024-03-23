package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Student_Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface Student_Details_Repo extends JpaRepository<Student_Details, Integer> {
    @Query("SELECT s.StudentID FROM Student_Details s WHERE s.userDetails.auth.email = :email")

    Optional<Integer> findByUserDetails_Auth_Email(String email);
}
