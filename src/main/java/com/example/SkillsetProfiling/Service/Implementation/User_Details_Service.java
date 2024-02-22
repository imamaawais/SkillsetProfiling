package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.User_Details_DTO;
import com.example.SkillsetProfiling.Entity.Role;
import com.example.SkillsetProfiling.Entity.User_Details;
import com.example.SkillsetProfiling.Exception.DuplicateUserDetailsException;
import com.example.SkillsetProfiling.Exception.UserDetailsNotFoundException;
import com.example.SkillsetProfiling.Repository.User_Details_Repo;
import com.example.SkillsetProfiling.Service.Interface.IUser_Details_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class User_Details_Service implements IUser_Details_Service {

    private User_Details_Repo userDetailsRepo;
    private ModelMapper mapper;

    @Override
    public User_Details_DTO addUserDetails(User_Details_DTO UserDetailsDTO) {
        User_Details user_details = mapper.map(UserDetailsDTO, User_Details.class);

        // Check if a user with the same ID already exists
        if (userDetailsRepo.findById(user_details.getUserID()).isPresent())
            throw new DuplicateUserDetailsException("User with the same ID already exists: " + user_details.getUserID());

        // Save to the database
        User_Details savedUserDetails = userDetailsRepo.save(user_details);
        User_Details_DTO savedUserDetailsDTO = mapper.map(savedUserDetails, User_Details_DTO.class);
        return savedUserDetailsDTO;
    }
    @Override
    public User_Details_DTO getUserDetailsByUserID(Integer userID) {
        Optional<User_Details> userDetailsOptional = userDetailsRepo.findById(userID);

        if (userDetailsOptional.isPresent()) {
            return mapper.map(userDetailsOptional.get(), User_Details_DTO.class);
        } else {
            throw new UserDetailsNotFoundException("User details not found with ID: " + userID);
        }
    }

    @Override
    public List<User_Details_DTO> getAllUserDetails() {
        List<User_Details> allUserDetails = userDetailsRepo.findAll();
        return allUserDetails.stream()
                .map(userDetails -> mapper.map(userDetails, User_Details_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public User_Details_DTO updateUserDetails(Integer userID, User_Details_DTO updatedUserDetailsDTO) {
        Optional<User_Details> existingUserDetailsOptional = userDetailsRepo.findById(userID);

        if (existingUserDetailsOptional.isPresent()) {
            User_Details existingUserDetails = existingUserDetailsOptional.get();
            // Update user details based on updatedUserDetailsDTO
            existingUserDetails.setFirstName(updatedUserDetailsDTO.getFirstName());
            existingUserDetails.setLastName(updatedUserDetailsDTO.getLastName());
            existingUserDetails.setDateOfBirth(updatedUserDetailsDTO.getDateOfBirth());
            existingUserDetails.setProfilePicture(updatedUserDetailsDTO.getProfilePicture());
            existingUserDetails.setRole(mapper.map(updatedUserDetailsDTO.getRole(), Role.class));


            User_Details updatedUserDetails = userDetailsRepo.save(existingUserDetails);
            return mapper.map(updatedUserDetails, User_Details_DTO.class);
        } else {
            throw new UserDetailsNotFoundException("User details not found with ID: " + userID);
        }
    }

    @Override
    @Transactional
    public boolean deleteUserDetails(Integer userID) {
        Optional<User_Details> userDetailsOptional = userDetailsRepo.findById(userID);

        if (userDetailsOptional.isPresent()) {
            userDetailsRepo.deleteById(userID);
            return true; // Deletion successful
        } else {
            throw new UserDetailsNotFoundException("User details not found with ID: " + userID);
        }
    }

}
