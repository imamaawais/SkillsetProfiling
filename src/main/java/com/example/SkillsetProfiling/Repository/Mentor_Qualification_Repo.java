package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Mentor_Qualification;
import com.example.SkillsetProfiling.Key.Mentor_Qualification_Key;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Mentor_Qualification_Repo extends JpaRepository<Mentor_Qualification, Mentor_Qualification_Key> {
//    Optional<Mentor_Qualification> findByIds(Integer mentorID, Integer qualificationID, Integer domainID);
//
//    @Modifying
//    @Query("DELETE FROM Mentor_Qualification m WHERE m.MentorID.id = :mentorID AND m.QualificationID.id = :qualificationID AND m.DomainID.id = :domainID")
//    void deleteByIds(@Param("mentorID") Integer mentorID, @Param("qualificationID") Integer qualificationID, @Param("domainID") Integer domainID);
//void deleteByIds(Integer mentorID, Integer qualificationID, Integer domainID);

    Optional<Mentor_Qualification> findById(Mentor_Qualification_Key id);

   void deleteById(Mentor_Qualification_Key id);
}
