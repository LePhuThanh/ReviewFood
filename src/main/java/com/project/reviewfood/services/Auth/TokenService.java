package com.project.reviewfood.services.Auth;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    public String generateJwt(Authentication auth);
}
