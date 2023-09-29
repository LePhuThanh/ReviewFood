package com.project.reviewfood.services;

import com.project.reviewfood.entities.User;
import com.project.reviewfood.entities.enums.FoodType;
import com.project.reviewfood.entities.enums.Sex;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUser();
    User getUserByUserName(String userName);
    User getUserByUserId(Long userId);
    User getUserByPhone(Integer phone);
    List<User> getUserBySex(Sex sex);
    List<User> getUserByAge(Integer age);
    List<User> getUserByFavouriteFoodType(FoodType foodType);
}
