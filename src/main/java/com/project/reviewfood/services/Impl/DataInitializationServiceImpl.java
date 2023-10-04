package com.project.reviewfood.services.Impl;

import com.project.reviewfood.entities.Role;
import com.project.reviewfood.entities.User;
import com.project.reviewfood.entities.enums.FoodType;
import com.project.reviewfood.entities.enums.Sex;
import com.project.reviewfood.repositories.RoleRepository;
import com.project.reviewfood.repositories.UserRepository;
import com.project.reviewfood.services.DataInitializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DataInitializationServiceImpl implements DataInitializationService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncode;

    @Override
    public void initializeData() {
        if(roleRepository.findRoleByAuthority("ADMIN").isPresent()) return;
        Role adminRole = roleRepository.save(new Role("ADMIN"));

        // Create is USER role into DB
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
    }
}
