package com.project.reviewfood;

import com.project.reviewfood.entities.Role;
import com.project.reviewfood.entities.User;
import com.project.reviewfood.entities.enums.FoodType;
import com.project.reviewfood.entities.enums.Sex;
import com.project.reviewfood.repositories.RoleRepository;
import com.project.reviewfood.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ReviewfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewfoodApplication.class, args);
	}
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
		return args ->{
			if(roleRepository.findRoleByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			User admin = new User(1,
					"Phu Thanh",
					"Le",
					"admin",
					passwordEncode.encode("password"),
					"0375254688",
					FoodType.ALL_FOOD,
					Sex.MALE,
					24,
					"phuthanh@gmail.com",
					"Binh Duong",
					roles);
			userRepository.save(admin);
		};
	}

}
