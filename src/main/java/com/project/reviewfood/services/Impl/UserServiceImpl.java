package com.project.reviewfood.services.Impl;

import com.project.reviewfood.entities.User;
import com.project.reviewfood.entities.enums.FoodType;
import com.project.reviewfood.entities.enums.Sex;
import com.project.reviewfood.repositories.UserRepository;
//import com.project.reviewfood.security.CustomUserDetails;
import com.project.reviewfood.security.CustomUserDetails;
import com.project.reviewfood.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User getUserByUserId(Long userId) {
        return userRepository.findUserByUserId(userId);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userRepository.findUserByPhone(phone);
    }

    @Override
    public List<User> getUserBySex(Sex sex) {
        return userRepository.findUserBySex(sex);
    }

    @Override
    public List<User> getUserByAge(Integer age) {
        return userRepository.findUserByAge(age);
    }

    @Override
    public List<User> getUsersByAgeGreaterThan(Integer age) {
        return userRepository.findUsersByAgeGreaterThan(age);
    }

    @Override
    public List<User> getUserByFoodType(FoodType foodType) {
        return userRepository.findUserByFoodType(foodType);
    }

    @Override
    public List<User> getUsersByAgeBetweenAndSex(Integer minAge, Integer maxAge, Sex sex) {
        return userRepository.findUsersByAgeBetweenAndSex(minAge, maxAge, sex);
    }

    //implement from UserDetailsService
    //When a user log in, SS will need to get the existing UserDetails information to check
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user != null){
            System.out.println("In the user details service");
            return new CustomUserDetails(user);
        }
        throw  new UsernameNotFoundException(username + " User is not valid");
    }


}
