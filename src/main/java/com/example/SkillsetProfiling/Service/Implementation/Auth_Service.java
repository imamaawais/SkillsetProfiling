package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Auth_DTO;
import com.example.SkillsetProfiling.Entity.Auth;
import com.example.SkillsetProfiling.Repository.Auth_Repo;
import com.example.SkillsetProfiling.Service.Interface.IAuth_Service;
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
    public Auth_DTO addAuth(Auth_DTO AuthDTO) {

        Auth auth = mapper.map(AuthDTO, Auth.class);

        //save to database
        Auth savedAuth = AuthRepo.save(auth);

        Auth_DTO savedAuthDTO = mapper.map(savedAuth, Auth_DTO.class);

        return savedAuthDTO;
    }

    public List<Auth_DTO> getAllUsers() {
        List<Auth> allUsers = AuthRepo.findAll(); // Assuming AuthRepo extends JpaRepository<Auth, Long>
        return allUsers.stream()
                .map(user -> mapper.map(user, Auth_DTO.class))
                .collect(Collectors.toList());
    }
}
