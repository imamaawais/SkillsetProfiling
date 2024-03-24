package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Mentor_Details_DTO;
import com.example.SkillsetProfiling.Entity.Mentor_Details;
import com.example.SkillsetProfiling.Exception.DuplicateMentorDetailsException;
import com.example.SkillsetProfiling.Exception.MentorDetailsNotFoundException;
import com.example.SkillsetProfiling.Repository.Mentor_Details_Repo;
import com.example.SkillsetProfiling.Service.Interface.IMentor_Details_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Mentor_Details_Service implements IMentor_Details_Service {
    private Mentor_Details_Repo mentorDetailsRepo;
    private ModelMapper mapper;

    @Override
    public Mentor_Details_DTO addMentorDetails(Mentor_Details_DTO mentorDetailsDTO) {
        Mentor_Details mentorDetails = mapper.map(mentorDetailsDTO, Mentor_Details.class);

        // Check if a mentor with the same ID already exists
        if (mentorDetailsRepo.findById(mentorDetails.getMentorID()).isPresent())
            throw new DuplicateMentorDetailsException("Mentor with the same ID already exists: " + mentorDetails.getMentorID());

        // Save to the database
        Mentor_Details savedMentorDetails = mentorDetailsRepo.save(mentorDetails);
        Mentor_Details_DTO savedMentorDetailsDTO = mapper.map(savedMentorDetails, Mentor_Details_DTO.class);
        return savedMentorDetailsDTO;
    }

    @Override
    public Mentor_Details_DTO getMentorDetailsByMentorID(Integer mentorID) {
        Optional<Mentor_Details> mentorDetailsOptional = mentorDetailsRepo.findById(mentorID);

        if (mentorDetailsOptional.isPresent()) {
            return mapper.map(mentorDetailsOptional.get(), Mentor_Details_DTO.class);
        } else {
            throw new MentorDetailsNotFoundException("Mentor details not found with ID: " + mentorID);
        }
    }

    @Override
    public Integer getMentorIDByEmail(String email) {
        Optional<Integer> mentorDetailsOptional = mentorDetailsRepo.findByUserDetails_Auth_Email(email);

        if (mentorDetailsOptional.isPresent()) {
            return mentorDetailsOptional.get();
        } else {
            throw new MentorDetailsNotFoundException("Mentor details not found with email: " + email);
        }
    }

    @Override
    public List<Mentor_Details_DTO> getAllMentorDetails() {
        List<Mentor_Details> allMentorDetails = mentorDetailsRepo.findAll();
        return allMentorDetails.stream()
                .map(mentorDetails -> mapper.map(mentorDetails, Mentor_Details_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Mentor_Details_DTO updateMentorDetails(Integer mentorID, Mentor_Details_DTO updatedMentorDetailsDTO) {
        Optional<Mentor_Details> existingMentorDetailsOptional = mentorDetailsRepo.findById(mentorID);

        if (existingMentorDetailsOptional.isPresent()) {
            Mentor_Details existingMentorDetails = existingMentorDetailsOptional.get();
            // Update mentor details based on updatedMentorDetailsDTO
            // Update other fields as needed

            existingMentorDetails.setUserDetails(updatedMentorDetailsDTO.getUserDetails());

            Mentor_Details updatedMentorDetails = mentorDetailsRepo.save(existingMentorDetails);
            return mapper.map(updatedMentorDetails, Mentor_Details_DTO.class);
        } else {
            throw new MentorDetailsNotFoundException("Mentor details not found with ID: " + mentorID);
        }
    }

    @Override
    @Transactional
    public boolean deleteMentorDetails(Integer mentorID) {
        Optional<Mentor_Details> mentorDetailsOptional = mentorDetailsRepo.findById(mentorID);

        if (mentorDetailsOptional.isPresent()) {
            mentorDetailsRepo.deleteById(mentorID);
            return true; // Deletion successful
        } else {
            throw new MentorDetailsNotFoundException("Mentor details not found with ID: " + mentorID);
        }
    }
}
