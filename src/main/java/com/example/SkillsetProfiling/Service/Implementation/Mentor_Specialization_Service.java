package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Mentor_Specialization_DTO;
import com.example.SkillsetProfiling.Entity.Mentor_Specialization;
import com.example.SkillsetProfiling.Exception.DuplicateMentorSpecializationException;
import com.example.SkillsetProfiling.Exception.MentorSpecializationNotFoundException;
import com.example.SkillsetProfiling.Key.Mentor_Specialization_Key;
import com.example.SkillsetProfiling.Repository.Mentor_Specialization_Repo;
import com.example.SkillsetProfiling.Service.Interface.IMentor_Specialization_Service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Mentor_Specialization_Service implements IMentor_Specialization_Service {

    private final Mentor_Specialization_Repo mentorSpecializationRepo;
    private final ModelMapper modelMapper;

    public Mentor_Specialization_Service(Mentor_Specialization_Repo mentorSpecializationRepo, ModelMapper modelMapper) {
        this.mentorSpecializationRepo = mentorSpecializationRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Mentor_Specialization_DTO addMentorSpecialization(Mentor_Specialization_DTO mentorSpecializationDTO) {
        Mentor_Specialization mentorSpecialization = modelMapper.map(mentorSpecializationDTO, Mentor_Specialization.class);
        if (mentorSpecializationRepo.findById(new Mentor_Specialization_Key(mentorSpecialization.getMentorID().getMentorID(), mentorSpecialization.getSkillID().getSkillID())).isPresent()) {
            throw new DuplicateMentorSpecializationException("Mentor specialization already exists with ID: " + mentorSpecialization.getMentorID().getMentorID());
        }
        Mentor_Specialization savedMentorSpecialization = mentorSpecializationRepo.save(mentorSpecialization);
        return modelMapper.map(savedMentorSpecialization, Mentor_Specialization_DTO.class);
    }

    @Override
    public Mentor_Specialization_DTO getMentorSpecializationByIds(Integer mentorId, Integer skillId) throws MentorSpecializationNotFoundException {
        Optional<Mentor_Specialization> mentorSpecializationOptional = mentorSpecializationRepo.findById(new Mentor_Specialization_Key(mentorId, skillId));
        if (mentorSpecializationOptional.isPresent()) {
            return modelMapper.map(mentorSpecializationOptional.get(), Mentor_Specialization_DTO.class);
        } else {
            throw new MentorSpecializationNotFoundException("Mentor specialization not found with IDs: " + mentorId + ", " + skillId);
        }
    }

    @Override
    public List<Mentor_Specialization_DTO> getAllMentorSpecializations() {
        List<Mentor_Specialization> mentorSpecializations = mentorSpecializationRepo.findAll();
        return mentorSpecializations.stream()
                .map(mentorSpecialization -> modelMapper.map(mentorSpecialization, Mentor_Specialization_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Mentor_Specialization_DTO updateMentorSpecialization(Integer mentorId, Integer skillId, Mentor_Specialization_DTO updatedMentorSpecializationDTO) throws MentorSpecializationNotFoundException {
        Optional<Mentor_Specialization> mentorSpecializationOptional = mentorSpecializationRepo.findById(new Mentor_Specialization_Key(mentorId, skillId));
        if (mentorSpecializationOptional.isPresent()) {
            Mentor_Specialization existingMentorSpecialization = mentorSpecializationOptional.get();
            existingMentorSpecialization.setMentorID(updatedMentorSpecializationDTO.getMentorDetails());
            existingMentorSpecialization.setSkillID(updatedMentorSpecializationDTO.getSkills());
            Mentor_Specialization updatedMentorSpecialization = mentorSpecializationRepo.save(existingMentorSpecialization);
            return modelMapper.map(updatedMentorSpecialization, Mentor_Specialization_DTO.class);
        } else {
            throw new MentorSpecializationNotFoundException("Mentor specialization not found with IDs: " + mentorId + ", " + skillId);
        }
    }

    @Override
    public boolean deleteMentorSpecialization(Integer mentorId, Integer skillId) throws MentorSpecializationNotFoundException {
        Optional<Mentor_Specialization> mentorSpecializationOptional = mentorSpecializationRepo.findById(new Mentor_Specialization_Key(mentorId, skillId));
        if (mentorSpecializationOptional.isPresent()) {
            mentorSpecializationRepo.delete(mentorSpecializationOptional.get());
            return true;
        } else {
            throw new MentorSpecializationNotFoundException("Mentor specialization not found with IDs: " + mentorId + ", " + skillId);
        }
    }
}
