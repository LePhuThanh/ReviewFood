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

}
