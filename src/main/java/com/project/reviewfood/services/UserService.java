package com.project.reviewfood.services;

import com.project.reviewfood.entities.User;
import com.project.reviewfood.entities.enums.FoodType;
import com.project.reviewfood.entities.enums.Sex;
import com.project.reviewfood.payloads.requests.UpdateUserRequest;
import com.project.reviewfood.payloads.responses.DataResponse;
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


    User updateInfUser(Long userId, UpdateUserRequest updateUserRequest);

    Boolean sendOtpViaEmail(String email);

    Boolean verifyUserEmail(String email, String otp);

    Boolean resetPassword(String username, String password, String newPassword);
}
