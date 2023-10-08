package com.project.reviewfood.services.Impl;

import com.project.reviewfood.entities.User;
import com.project.reviewfood.entities.enums.FoodType;
import com.project.reviewfood.entities.enums.Sex;
import com.project.reviewfood.handlers.CustomException;
import com.project.reviewfood.payloads.requests.UpdateUserRequest;
import com.project.reviewfood.repositories.UserRepository;
import com.project.reviewfood.security.CustomUserDetails;
import com.project.reviewfood.services.UserService;
import com.project.reviewfood.util.EmailUtil;
import com.project.reviewfood.util.OtpUtil;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private OtpUtil otpUtil;
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

    @Override
    public User updateInfUser(Long userId, UpdateUserRequest updateUserRequest) {
        User existUser = userRepository.findUserByUserId(userId);
        if (existUser != null){
            existUser.setFirstname(updateUserRequest.getFirstname());
            existUser.setLastname(updateUserRequest.getLastname());
            existUser.setPhone(updateUserRequest.getPhone());
            existUser.setFoodType(updateUserRequest.getFoodType());
            existUser.setSex(updateUserRequest.getSex());
            existUser.setAge(updateUserRequest.getAge());
            existUser.setEmail(updateUserRequest.getEmail());
            existUser.setHometown(updateUserRequest.getHometown());
            return userRepository.save(existUser);
        }
        throw new CustomException("404", "Not found user");
    }

    @Override
    public Boolean sendOtpViaEmail(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new CustomException("404","User not found with this email: " + email));

        String otp = otpUtil.generateOtp();
        user.setOtp(otp);
        user.setOtpGenerateTime(LocalDateTime.now());
        userRepository.save(user);
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e){
            throw new CustomException("500","Unable to send otp please try again!");
        }
        return true;
    }

    @Override
    public Boolean verifyUserEmail(String email, String otp) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new CustomException("404","User not found with this email: " + email));
        if (user.getOtp().equals(otp) && Duration.between(user.getOtpGenerateTime(),
                LocalDateTime.now()).getSeconds() < (2 * 60)) {
            // Verified Email
            user.setActiveEmail(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public Boolean resetPassword(String username, String password, String newPassword) {
        try {
            User user = userRepository.findUserByUsername(username.trim());
            if (user != null && Objects.equals(user.getPassword(), password)) { // equals compare string; == compare object => != location memory
                //Encode Password
                String encodedPassword = passwordEncoder.encode(newPassword);
                user.setPassword(encodedPassword);
                userRepository.save(user);
            }
        } catch (EntityNotFoundException e){
            throw new CustomException("404", "User not found with this username: " + username);
        }
        return true;
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
