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
        return null;
    }

    @Override
    public User getUserByUserId(Long userId) {
        return null;
    }

    @Override
    public User getUserByPhone(Integer phone) {
        return null;
    }

    @Override
    public List<User> getUserBySex(Sex sex) {
        return null;
    }

    @Override
    public List<User> getUserByAge(Integer age) {
        return null;
    }

    @Override
    public List<User> getUserByFavouriteFoodType(FoodType foodType) {
        return null;
    }
}
