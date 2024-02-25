package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Mentor_Qualification_DTO;
import com.example.SkillsetProfiling.Entity.Mentor_Qualification;
import com.example.SkillsetProfiling.Exception.DuplicateMentorQualificationException;
import com.example.SkillsetProfiling.Exception.MentorDetailsNotFoundException;
import com.example.SkillsetProfiling.Key.Mentor_Qualification_Key;
import com.example.SkillsetProfiling.Repository.Mentor_Qualification_Repo;
import com.example.SkillsetProfiling.Service.Interface.IMentor_Qualification_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Mentor_Qualification_Service implements IMentor_Qualification_Service {

    private static final Logger logger = LoggerFactory.getLogger(Mentor_Qualification_Service.class);

    private final Mentor_Qualification_Repo mentorQualificationRepo;
    private final ModelMapper mapper;


    @Override
    public Mentor_Qualification_DTO addMentorQualification(Mentor_Qualification_DTO mentorQualificationDTO) {
        Mentor_Qualification mentorQualification = mapper.map(mentorQualificationDTO, Mentor_Qualification.class);

        Mentor_Qualification_Key mentorQualificationKey = new Mentor_Qualification_Key();
        mentorQualificationKey.setMentorID(mentorQualification.getMentorID().getMentorID());
        mentorQualificationKey.setQualificationID(mentorQualification.getQualificationID().getQualificationID());
        mentorQualificationKey.setDomainID(mentorQualification.getDomainID().getDomainID());

        logger.warn("Mentor qualification key: {}", mentorQualificationKey);

        // Check if a mentor qualification with the same key already exists
        Optional<Mentor_Qualification> existingMentorQualification = mentorQualificationRepo.findById(mentorQualificationKey);
        if (existingMentorQualification.isPresent()) {
            throw new DuplicateMentorQualificationException("Mentor qualification with the same key already exists: " + mentorQualificationKey);
        }

        Mentor_Qualification savedMentorQualification = mentorQualificationRepo.save(mentorQualification);
        Mentor_Qualification_DTO savedMentorQualificationDTO = mapper.map(savedMentorQualification, Mentor_Qualification_DTO.class);
        return savedMentorQualificationDTO;
    }
    @Override
    public List<Mentor_Qualification_DTO> getAllMentorQualifications() {
        List<Mentor_Qualification> allMentorQualifications = mentorQualificationRepo.findAll();
        return allMentorQualifications.stream()
                .map(mentorQualification -> mapper.map(mentorQualification, Mentor_Qualification_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Mentor_Qualification_DTO getMentorQualificationById(Mentor_Qualification_Key key) {
        Optional<Mentor_Qualification> mentorQualificationOptional = mentorQualificationRepo.findById(key);

        if ( mentorQualificationOptional.isPresent()){
            return mapper.map(mentorQualificationOptional.get(),Mentor_Qualification_DTO.class);
        } else {
            throw new MentorDetailsNotFoundException("Mentor Qualification not found with ID: " +key);
        }


    }

    @Override
    public Mentor_Qualification_DTO updateMentorQualification(Mentor_Qualification_Key key, Mentor_Qualification_DTO updatedMentorQualificationDTO) {
        Optional<Mentor_Qualification> existingMentorQualificationOptional = mentorQualificationRepo.findById(key);

        if (existingMentorQualificationOptional.isPresent()) {
            Mentor_Qualification existingMentorQualification = existingMentorQualificationOptional.get();

            // Update mentor qualification details based on updatedMentorQualificationDTO
            existingMentorQualification.setCompleted(updatedMentorQualificationDTO.getCompleted());
            // updating FKs
//            existingMentorQualification.setQualificationID(updatedMentorQualificationDTO.getQualification());
//            existingMentorQualification.setDomainID(updatedMentorQualificationDTO.getDomain());

            Mentor_Qualification updatedMentorQualification = mentorQualificationRepo.save(existingMentorQualification);
            return mapper.map(updatedMentorQualification, Mentor_Qualification_DTO.class);
        } else {
            throw new MentorDetailsNotFoundException("Mentor qualification not found with key: " + key);
        }
    }


    @Override
    public boolean deleteMentorQualification(Mentor_Qualification_Key key) {
        logger.info("Deleting mentor qualification with key: {}", key);
        Optional<Mentor_Qualification> mentorQualificationOptional = mentorQualificationRepo.findById(key);

        if(mentorQualificationOptional.isPresent() ){
            mentorQualificationRepo.deleteById(key);
            logger.info("Mentor qualification deleted successfully");
            return true;
        } else{
            logger.warn("Mentor qualification not found with key: {}", key);
            throw new MentorDetailsNotFoundException("Mentor qualification not found with ID: " + key);
        }


    }

}
