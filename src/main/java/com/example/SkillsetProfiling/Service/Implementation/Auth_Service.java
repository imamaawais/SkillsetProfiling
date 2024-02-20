package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Auth_DTO;
import com.example.SkillsetProfiling.Entity.Auth;
import com.example.SkillsetProfiling.Repository.Auth_Repo;
import com.example.SkillsetProfiling.Service.Interface.IAuth_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Auth_Service implements IAuth_Service {

    private Auth_Repo AuthRepo;
    private ModelMapper mapper;

    @Override
    public Auth_DTO addAuth(Auth_DTO AuthDTO) { // does not check if already exists or not
        Auth auth = mapper.map(AuthDTO, Auth.class);
        //save to database
        Auth savedAuth = AuthRepo.save(auth);
        Auth_DTO savedAuthDTO = mapper.map(savedAuth, Auth_DTO.class);
        return savedAuthDTO;
    }

    @Override
    public Auth_DTO getUserByEmail(String email) {
        Auth user = AuthRepo.findByEmail(email).orElse(null);

        if (user == null) {
            return null; // User not found
        }

        return mapper.map(user, Auth_DTO.class);
    }


    @Override
    public List<Auth_DTO> getAllUsers() {
        List<Auth> allUsers = AuthRepo.findAll();
        return allUsers.stream()
                .map(user -> mapper.map(user, Auth_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Auth_DTO updateUser(String email, Auth_DTO updatedAuthDTO) {
        Auth existingUser = AuthRepo.findByEmail(email).orElse(null);

        if (existingUser == null) {
            return null; // User not found
        }

        // Update user information based on updatedAuthDTO
        // For example:
        existingUser.setPassword(updatedAuthDTO.getPassword());
        existingUser.setPhone_number(updatedAuthDTO.getPhone_number());

        Auth updatedUser = AuthRepo.save(existingUser);
        return mapper.map(updatedUser, Auth_DTO.class);
    }

    @Override
    @Transactional
    public boolean deleteUser(String email) {
        if (AuthRepo.existsByEmail(email)) {
            AuthRepo.deleteByEmail(email);
            return true; // Deletion successful
        }
        return false; // User not found
    }
}
