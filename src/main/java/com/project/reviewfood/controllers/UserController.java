package com.project.reviewfood.controllers;

import com.project.reviewfood.entities.User;
import com.project.reviewfood.entities.enums.FoodType;
import com.project.reviewfood.entities.enums.Sex;
import com.project.reviewfood.handlers.CustomException;
import com.project.reviewfood.payloads.requests.UpdateUserRequest;
import com.project.reviewfood.payloads.responses.DataResponse;
import com.project.reviewfood.services.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping(value = "/getAllUser")
    public ResponseEntity<DataResponse> getAllUser(){
        List<User> userList = userService.getAllUser();
        if (!userList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Get all users successfully", userList));
        }
        throw new CustomException("404","Not found any users");
    }
    @GetMapping(value = "/getUserByUserName")
    public ResponseEntity<DataResponse> getUserByUserName(@PathParam("userName") String userName){
        User user = userService.getUserByUsername(userName);
        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Get user by user name successfully", user));
        }
        throw new CustomException("404", "Not found any users");
    }
    @GetMapping(value = "/getUserByUserId/{userId}")
    public ResponseEntity<DataResponse> getUserByUserId(@PathVariable Long userId){
        User user = userService.getUserByUserId(userId);
        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Get user by user id successfully", user));
        }
        throw new CustomException("404", "Not found any users");
    }
    @GetMapping(value = "/getUserByPhone")
    public ResponseEntity<DataResponse> getUserByPhone(@PathParam("phone") String phone){
        User user = userService.getUserByPhone(phone);
        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Get user by phone successfully", user));
        }
        throw new CustomException("404", "Not found any users");
    }
    @GetMapping(value = "/getUserBySex")
    public ResponseEntity<DataResponse> getUserBySex(@PathParam("sex") Sex sex){
        List<User> userList = userService.getUserBySex(sex);
        if(userList.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Get user by sex successfully", userList));
        }
        throw new CustomException("404", "Not found any users");
    }
    @GetMapping(value = "/getUserByAge")
    public ResponseEntity<DataResponse> getUserByAge(@PathParam("age") Integer age){
        List<User> userList = userService.getUserByAge(age);
        if(userList.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Get user by age successfully", userList));
        }
        throw new CustomException("404", "Not found any users");
    }
    @GetMapping(value = "/getUsersByAgeGreaterThan/{age}")
    public ResponseEntity<DataResponse> getUsersByAgeGreaterThan(@PathVariable Integer age){
        List<User> userList = userService.getUsersByAgeGreaterThan(age);
        if(userList.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Get user by age greater than " + age + " successfully", userList));
        }
        throw new CustomException("404", "Not found any users");
    }

    @GetMapping(value = "/getUserByFavouriteFoodType")
    public ResponseEntity<DataResponse> getUserByFavouriteFoodType(@PathParam("foodType") FoodType foodType){
        List<User> userList = userService.getUserByFoodType(foodType);
        if(userList.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Get user by favourite food type successfully", userList));
        }
        throw new CustomException("404", "Not found any users");
    }

    @GetMapping(value = "/getUsersByAgeBetweenAndSex")
    public ResponseEntity<DataResponse> getUsersByAgeBetweenAndSex(@RequestParam("minAge") Integer minAge,
                                                                   @RequestParam("maxAge") Integer maxAge,
                                                                   @RequestParam("sex") Sex sex){
        List<User> userList = userService.getUsersByAgeBetweenAndSex(minAge, maxAge, sex);
        if(userList.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Get user by age successfully", userList));
        }
        throw new CustomException("404", "Not found any users");
    }

    @PutMapping(value = "/updateInfUser/{userId}")
    public ResponseEntity<DataResponse> updateInfUser(@PathVariable Long userId,
                                                      @RequestBody UpdateUserRequest updateUserRequest) {
        User updateUser = userService.updateInfUser(userId, updateUserRequest);
        if(updateUser != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Update the user successfully", updateUser));
        }
        throw new CustomException("500", "Update the user failed");
    }

    @GetMapping(value = "/getOtpToVerifyUserEmail")
    public ResponseEntity<DataResponse> getOtpToVerifyUserEmail(@RequestParam String email) {
        Boolean isSendOtp = userService.sendOtpViaEmail(email);
        if(isSendOtp) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Send Otp Successfully", true));
        }
        throw new CustomException("500", "Unable to send otp please try again!");
    }
    @PutMapping(value = "/verifyUserEmail")
    public ResponseEntity<DataResponse> verifyUserEmail(@RequestParam String email, String otp) {
        Boolean isVerifiedEmail = userService.verifyUserEmail(email, otp);
        if(isVerifiedEmail) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "OTP verified you can use function forgot password", true));
        }
        throw new CustomException("500", "Please regenerate otp and try again!");
    }
    @PutMapping(value = "/regenerateOtp")
    public ResponseEntity<DataResponse> regenerateOtp(@RequestParam String email) {
        Boolean isRegenerateOtp = userService.sendOtpViaEmail(email);
        if(isRegenerateOtp) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Email send ... sent please verify account within 2 minute", true));
        }
        throw new CustomException("500", "Regenerate otp failed and try again!");
    }

}
