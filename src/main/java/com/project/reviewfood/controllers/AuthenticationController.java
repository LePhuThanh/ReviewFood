package com.project.reviewfood.controllers;

import com.project.reviewfood.entities.User;
import com.project.reviewfood.handlers.CustomException;
import com.project.reviewfood.payloads.requests.LoginRequest;
import com.project.reviewfood.payloads.requests.RegisterUserRequest;
import com.project.reviewfood.payloads.responses.DataResponse;
import com.project.reviewfood.payloads.responses.LoginResponse;
import com.project.reviewfood.services.Auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping(value = "/register")
    public ResponseEntity<DataResponse> registerUser(@RequestBody RegisterUserRequest request){
        User user = authenticationService.registerUser(request);
        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Register account successfully", user));
        }
        throw new CustomException("404", "Register account failed");
    }
    @PostMapping(value = "/login")
    public ResponseEntity<DataResponse> loginUser(@RequestBody LoginRequest request){
        LoginResponse loginUser = authenticationService.loginUser(request);
        if(loginUser.getUser() != null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Login successfully", loginUser));
        }
        throw new CustomException("404", "Not found any users");
    }

    @PutMapping(value = "/forgotPassword")
    public ResponseEntity<DataResponse> forgotPassword(@RequestParam("email") String email) {
        Boolean isRegenerateOtp = authenticationService.forgotPassword(email);
        if(isRegenerateOtp) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Please check your email to set password to your account", true));
        }
        throw new CustomException("500", "There are errors when send email to set to password!");
    }
    @PutMapping(value = "/setNewPassword")
    public ResponseEntity<DataResponse> setNewPassword(@RequestParam("email") String email,
                                                       @RequestHeader("newPassword") String newPassword) {
        //NewPassword is passed through the HTTP request header, possibly because the user wants to securely transmit the new password without displaying it in the URL query or in the body of the request.
        //This way, the new password will not appear in the logs or in the query history.
        Boolean isSetNewPassword = authenticationService.setNewPassword(email, newPassword);
        if(isSetNewPassword) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "New password set successfully login with new password", true));
        }
        throw new CustomException("500", "There are errors when set new password!");
    }
}
