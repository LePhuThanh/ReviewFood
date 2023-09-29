package com.project.reviewfood.controllers;

import com.project.reviewfood.entities.User;
import com.project.reviewfood.handlers.CustomException;
import com.project.reviewfood.payloads.responses.DataResponse;
import com.project.reviewfood.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
