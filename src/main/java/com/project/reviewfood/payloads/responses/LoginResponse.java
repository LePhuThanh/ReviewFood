package com.project.reviewfood.payloads.responses;

import com.project.reviewfood.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//DTO - Data Transfer Object
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private User user;
    private String jwt;
}
