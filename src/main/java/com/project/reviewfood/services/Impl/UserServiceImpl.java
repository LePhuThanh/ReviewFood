package com.project.reviewfood.services.Impl;

import com.project.reviewfood.entities.User;
import com.project.reviewfood.entities.enums.FoodType;
import com.project.reviewfood.entities.enums.Sex;
import com.project.reviewfood.repositories.UserRepository;
import com.project.reviewfood.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public User getUserByUserId(Long userId) {
        return userRepository.findUserByUserId(userId);
    }

    @Override
    public User getUserByPhone(Integer phone) {
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
}
