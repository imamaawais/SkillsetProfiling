package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.HR_Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HR_Details_Repo extends JpaRepository<HR_Details, Integer> {
    @Query("SELECT h.HrID FROM HR_Details h WHERE h.userDetails.auth.email = :email")
    Optional<Integer> findByUserDetails_Auth_Email(String email);
}
