package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Mentor_Specialization_DTO;
import com.example.SkillsetProfiling.Entity.Mentor_Specialization;
import com.example.SkillsetProfiling.Exception.DuplicateMentorSpecializationException;
import com.example.SkillsetProfiling.Exception.MentorSpecializationNotFoundException;
import com.example.SkillsetProfiling.Key.Mentor_Specialization_Key;
import com.example.SkillsetProfiling.Repository.Mentor_Specialization_Repo;
import com.example.SkillsetProfiling.Service.Interface.IMentor_Specialization_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Mentor_Specialization_Service implements IMentor_Specialization_Service {

    private final Mentor_Specialization_Repo mentorSpecializationRepo;
//    private final ModelMapper modelMapper;

    @Override
    public Mentor_Specialization_DTO addMentorSpecialization(Mentor_Specialization_DTO mentorSpecializationDTO) {
        System.out.println(mentorSpecializationDTO.getSkills().getSkillID());
        // Mapper was giving error for some reason so updated manually here
        Mentor_Specialization mentorSpecialization = new Mentor_Specialization(mentorSpecializationDTO.getMentorDetails(), mentorSpecializationDTO.getSkills());
        System.out.println("After map: "+mentorSpecialization.getSkillID());
        if (mentorSpecializationRepo.findById(new Mentor_Specialization_Key(mentorSpecialization.getMentorID().getMentorID(), mentorSpecialization.getSkillID().getSkillID())).isPresent()) {
            throw new DuplicateMentorSpecializationException("Mentor specialization already exists with ID: " + mentorSpecialization.getMentorID().getMentorID());
        }
        Mentor_Specialization savedMentorSpecialization = mentorSpecializationRepo.save(mentorSpecialization);
        return new Mentor_Specialization_DTO(savedMentorSpecialization.getMentorID(),savedMentorSpecialization.getSkillID());
    }

    @Override
    public Mentor_Specialization_DTO getMentorSpecializationByIds(Integer mentorId, Integer skillId) throws MentorSpecializationNotFoundException {
        Optional<Mentor_Specialization> mentorSpecializationOptional = mentorSpecializationRepo.findById(new Mentor_Specialization_Key(mentorId, skillId));
        if (mentorSpecializationOptional.isPresent()) {
            return new Mentor_Specialization_DTO(mentorSpecializationOptional.get().getMentorID(),mentorSpecializationOptional.get().getSkillID());
        } else {
            throw new MentorSpecializationNotFoundException("Mentor specialization not found with IDs: " + mentorId + ", " + skillId);
        }
    }

    @Override
    public List<Mentor_Specialization_DTO> getAllMentorSpecializations() {
        List<Mentor_Specialization> mentorSpecializations = mentorSpecializationRepo.findAll();
        return mentorSpecializations.stream()
                .map(mentorSpecialization -> new Mentor_Specialization_DTO(mentorSpecialization.getMentorID(),mentorSpecialization.getSkillID()))
                .collect(Collectors.toList());
    }

    @Override
    public Mentor_Specialization_DTO updateMentorSpecialization(Integer mentorId, Integer skillId, Mentor_Specialization_DTO updatedMentorSpecializationDTO) throws MentorSpecializationNotFoundException {
        Optional<Mentor_Specialization> mentorSpecializationOptional = mentorSpecializationRepo.findById(new Mentor_Specialization_Key(mentorId, skillId));
        if (mentorSpecializationOptional.isPresent()) {
            Mentor_Specialization existingMentorSpecialization = mentorSpecializationOptional.get();
//            existingMentorSpecialization.setMentorID(updatedMentorSpecializationDTO.getMentorDetails());
//            existingMentorSpecialization.setSkillID(updatedMentorSpecializationDTO.getSkills());
            Mentor_Specialization updatedMentorSpecialization = mentorSpecializationRepo.save(existingMentorSpecialization);
            return new Mentor_Specialization_DTO(updatedMentorSpecialization.getMentorID(),updatedMentorSpecialization.getSkillID());
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
