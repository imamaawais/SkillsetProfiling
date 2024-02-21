package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Auth_DTO;
import com.example.SkillsetProfiling.Exception.UserNotFoundException;

import java.util.List;

public interface IAuth_Service {

    Auth_DTO addAuth(Auth_DTO AuthDTO);
    Auth_DTO getUserByEmail(String email) throws UserNotFoundException;
    List<Auth_DTO> getAllUsers();
    Auth_DTO updateUser(String email, Auth_DTO updatedAuthDTO);

    boolean deleteUser(String email);
}
