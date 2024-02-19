package com.example.SkillsetProfiling;

import com.example.SkillsetProfiling.Dto.Auth_DTO;
import com.example.SkillsetProfiling.Repository.Auth_Repo;
import com.example.SkillsetProfiling.Service.Implementation.Auth_Service;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkillsetProfilingApplication {

	public static void main(String[] args) {


		SpringApplication.run(SkillsetProfilingApplication.class, args);

	}

}
