package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Role_DTO;
import com.example.SkillsetProfiling.Dto.Skill_Groups_DTO;
import com.example.SkillsetProfiling.Entity.Role;
import com.example.SkillsetProfiling.Entity.Skill_Groups;
import com.example.SkillsetProfiling.Exception.DuplicateSkillGroupException;
import com.example.SkillsetProfiling.Exception.UserNotFoundException;
import com.example.SkillsetProfiling.Repository.Skill_Groups_Repo;
import com.example.SkillsetProfiling.Service.Interface.ISkill_Groups_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Skill_Groups_Service implements ISkill_Groups_Service {

    private Skill_Groups_Repo skillGroupsRepo;
    private ModelMapper mapper;

    @Override
    public Skill_Groups_DTO addSkillGroup(Skill_Groups_DTO skillGroupsDTO) {

        Skill_Groups skillGroup = mapper.map(skillGroupsDTO, Skill_Groups.class);

        //check if skill group with this id already present
        if(skillGroupsRepo.findById(skillGroup.getSkillGroupID()).isPresent()){
            throw new DuplicateSkillGroupException("Skill Group with the same Id already exists: "+ skillGroup.getSkillGroupID());
        }

        //save to db
        Skill_Groups savedSkillGroup = skillGroupsRepo.save(skillGroup);
        Skill_Groups_DTO savedSkillGroupDTO = mapper.map(savedSkillGroup, Skill_Groups_DTO.class);

        return savedSkillGroupDTO;
    }

    @Override
    public Skill_Groups_DTO getSkillGroupById(Integer SkillGroupID) throws UserNotFoundException {
        return null;
    }

    @Override
    public List<Skill_Groups_DTO> getAllSkillGroups() {
        List<Skill_Groups> skillGroups = skillGroupsRepo.findAll();

        System.out.println(skillGroups);
        return skillGroups.stream()
                .map(skillGroup-> mapper.map(skillGroups, Skill_Groups_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Skill_Groups_DTO updateSkillGroup(String SkillGroupID, Skill_Groups_DTO updatedSkillGroupsDTO) {
        return null;
    }

    @Override
    public boolean deleteSkillGroup(Integer SkillGroupID) {
        return false;
    }
}
