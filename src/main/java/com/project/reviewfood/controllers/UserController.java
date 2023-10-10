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
    public ResponseEntity<DataResponse> getOtpToVerifyUserEmail(@RequestParam("email") String email) {
        Boolean isSendOtp = userService.sendOtpViaEmail(email);
        if(isSendOtp) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Send Otp Successfully", true));
        }
        throw new CustomException("500", "Unable to send otp please try again!");
    }
    @PutMapping(value = "/verifyUserEmail")
    public ResponseEntity<DataResponse> verifyUserEmail(@RequestParam("email") String email,
                                                        @RequestParam("otp") String otp) {
        Boolean isVerifiedEmail = userService.verifyUserEmail(email, otp);
        if(isVerifiedEmail) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "OTP verified you can use function forgot password", true));
        }
        throw new CustomException("500", "Please regenerate otp and try again!");
    }
    @PutMapping(value = "/regenerateOtp")
    public ResponseEntity<DataResponse> regenerateOtp(@RequestParam("email") String email) {
        Boolean isRegenerateOtp = userService.sendOtpViaEmail(email);
        if(isRegenerateOtp) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Email send ... sent please verify account within 2 minute", true));
        }
        throw new CustomException("500", "Regenerate otp failed and try again!");
    }
    @PutMapping(value = "/resetPassword")
    public ResponseEntity<DataResponse> resetPassword(@RequestParam("username") String username,
                                                      @RequestParam("password") String password,
                                                      @RequestHeader("newPassword") String newPassword) {
        Boolean isResetPassword = userService.resetPassword(username, password, newPassword);
        if(isResetPassword) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Reset password successfully login with the new password", true));
        }
        throw new CustomException("500", "There are errors when reset password!");
    }


}
