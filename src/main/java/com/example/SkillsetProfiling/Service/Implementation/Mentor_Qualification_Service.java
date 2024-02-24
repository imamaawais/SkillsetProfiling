package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Mentor_Qualification_DTO;
import com.example.SkillsetProfiling.Entity.Mentor_Qualification;
import com.example.SkillsetProfiling.Key.Mentor_Qualification_Key;
import com.example.SkillsetProfiling.Repository.Mentor_Qualification_Repo;
import com.example.SkillsetProfiling.Service.Interface.IMentor_Qualification_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Mentor_Qualification_Service implements IMentor_Qualification_Service {

    private final Mentor_Qualification_Repo mentorQualificationRepo;
    private final ModelMapper mapper;

    @Override
    public List<Mentor_Qualification_DTO> getAllMentorQualifications() {
        List<Mentor_Qualification> mentorQualifications = mentorQualificationRepo.findAll();
        return mentorQualifications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Mentor_Qualification_DTO> getMentorQualificationById(Mentor_Qualification_Key key) {
        Optional<Mentor_Qualification> mentorQualification = mentorQualificationRepo.findById(key);
        return mentorQualification.map(this::convertToDTO);
    }

    @Override
    public Mentor_Qualification_DTO saveMentorQualification(Mentor_Qualification_DTO mentorQualificationDTO) {
        Mentor_Qualification mentorQualification = convertToEntity(mentorQualificationDTO);
        Mentor_Qualification savedEntity = mentorQualificationRepo.save(mentorQualification);
        return convertToDTO(savedEntity);
    }

    @Override
    public void deleteMentorQualification(Mentor_Qualification_Key key) {
        mentorQualificationRepo.deleteById(key);
    }

    // Helper methods for conversion
    private Mentor_Qualification_DTO convertToDTO(Mentor_Qualification entity) {
        return mapper.map(entity, Mentor_Qualification_DTO.class);
    }

    private Mentor_Qualification convertToEntity(Mentor_Qualification_DTO dto) {
        return mapper.map(dto, Mentor_Qualification.class);
    }
}
