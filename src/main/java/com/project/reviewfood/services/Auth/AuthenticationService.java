package com.project.reviewfood.services.Auth;

import com.project.reviewfood.entities.User;
import com.project.reviewfood.payloads.requests.LoginRequest;
import com.project.reviewfood.payloads.requests.RegisterUserRequest;
import com.project.reviewfood.payloads.responses.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    User registerUser(RegisterUserRequest request);
    LoginResponse loginUser(LoginRequest request);
}
