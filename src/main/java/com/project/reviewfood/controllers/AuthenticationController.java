package com.project.reviewfood.controllers;

import com.project.reviewfood.entities.User;
import com.project.reviewfood.handlers.CustomException;
import com.project.reviewfood.payloads.requests.LoginRequest;
import com.project.reviewfood.payloads.requests.RegisterUserRequest;
import com.project.reviewfood.payloads.responses.DataResponse;
import com.project.reviewfood.payloads.responses.LoginResponse;
import com.project.reviewfood.services.Auth.AuthenticationService;
import jakarta.websocket.server.PathParam;
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
        if(loginUser != null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse("200", "Login successfully", loginUser));
        }
        throw new CustomException("404", "Not found any users");
    }

}
