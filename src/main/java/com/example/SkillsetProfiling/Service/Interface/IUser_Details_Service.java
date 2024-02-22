package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.User_Details_DTO;

import java.util.List;

public interface IUser_Details_Service {
    User_Details_DTO addUserDetails(User_Details_DTO UserDetailsDTO);
    User_Details_DTO getUserDetailsByUserID(Integer userID);
    List<User_Details_DTO> getAllUserDetails();
    User_Details_DTO updateUserDetails(Integer userID, User_Details_DTO updatedUserDetailsDTO );
    boolean deleteUserDetails(Integer userID);
}
