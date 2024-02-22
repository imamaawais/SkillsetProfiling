package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Auth_DTO;
import com.example.SkillsetProfiling.Entity.Auth;
import com.example.SkillsetProfiling.Exception.DuplicateAuthException;
import com.example.SkillsetProfiling.Exception.UserNotFoundException;
import com.example.SkillsetProfiling.Repository.Auth_Repo;
import com.example.SkillsetProfiling.Service.Interface.IAuth_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Auth_Service implements IAuth_Service {

    private Auth_Repo authRepo;
    private ModelMapper mapper;

    @Override
    public Auth_DTO addAuth(Auth_DTO AuthDTO) { // does not check if already exists or not
        Auth auth = mapper.map(AuthDTO, Auth.class);

        // Check if an Auth with the same ID already exists
        if (authRepo.findById(auth.getEmail()).isPresent()) {
            throw new DuplicateAuthException("Auth with the same ID already exists: " + auth.getEmail());
        }

        //save to database
        Auth savedAuth = authRepo.save(auth);
        Auth_DTO savedAuthDTO = mapper.map(savedAuth, Auth_DTO.class);
        return savedAuthDTO;
    }

    @Override
    public Auth_DTO getUserByEmail(String email) {
        Optional<Auth> user = authRepo.findByEmail(email);

        if (user.isPresent()) {
            return mapper.map(user, Auth_DTO.class);
        }else{
            throw new UserNotFoundException("User not found with email: " + email);
        }


    }




    @Override
    public List<Auth_DTO> getAllUsers() {
        List<Auth> allUsers = authRepo.findAll();
        return allUsers.stream()
                .map(user -> mapper.map(user, Auth_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Auth_DTO updateUser(String email, Auth_DTO updatedAuthDTO) {
        Auth existingUser = authRepo.findByEmail(email).orElse(null);

        if (existingUser == null) {
            return null; // User not found
        }

        // Update user information based on updatedAuthDTO
        // For example:
        existingUser.setPassword(updatedAuthDTO.getPassword());
        existingUser.setPhoneNumber(updatedAuthDTO.getPhoneNumber());

        Auth updatedUser = authRepo.save(existingUser);
        return mapper.map(updatedUser, Auth_DTO.class);
    }

    @Override
    @Transactional
    public boolean deleteUser(String email) throws UserNotFoundException {
        Optional<Auth> userOptional = authRepo.findByEmail(email);

        if (userOptional.isPresent()) {
            Auth userToDelete = userOptional.get();
            authRepo.delete(userToDelete);
            return true; // Deletion successful
        } else {
            throw new UserNotFoundException("User not found with email: " + email);
        }
    }
}
