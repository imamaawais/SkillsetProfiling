package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Mentor_Details;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface Mentor_Details_Repo extends JpaRepository<Mentor_Details, Integer> {
    @Query("SELECT m.MentorID FROM Mentor_Details m WHERE m.userDetails.auth.email = :email")
    Optional<Integer> findByUserDetails_Auth_Email(String email);
}
