package com.project.reviewfood.services.Auth;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import com.project.reviewfood.entities.User;
import com.project.reviewfood.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
public interface TokenService {
    String generateJwt(Authentication auth);
    //EXTRACT
    Map<String, Object> extractAllClaims(String token) throws Exception;
    String extractUsername(String token) throws Exception;
    Date extractExpiration(String token) throws Exception;
    //CHECK
    boolean isTokenExpired(String token) throws Exception;
    boolean isTokenValid(String token, UserDetails userDetails);
}
