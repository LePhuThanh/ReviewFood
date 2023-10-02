package com.project.reviewfood.services;

import com.project.reviewfood.entities.User;
import com.project.reviewfood.entities.enums.FoodType;
import com.project.reviewfood.entities.enums.Sex;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> getAllUser();
    User getUserByUsername(String username);
    User getUserByUserId(Long userId);
    User getUserByPhone(String phone);
    List<User> getUserBySex(Sex sex);
    List<User> getUserByAge(Integer age);
    List<User> getUsersByAgeGreaterThan(Integer age);
    List<User> getUserByFoodType(FoodType foodType);
    List<User> getUsersByAgeBetweenAndSex (Integer minAge, Integer maxAge, Sex sex);


}
