package com.project.reviewfood.services.Auth;
import com.project.reviewfood.entities.Role;
import com.project.reviewfood.entities.User;
import com.project.reviewfood.handlers.CustomException;
import com.project.reviewfood.payloads.requests.LoginRequest;
import com.project.reviewfood.payloads.requests.RegisterUserRequest;
import com.project.reviewfood.repositories.RoleRepository;
import com.project.reviewfood.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.reviewfood.payloads.responses.LoginResponse;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.Authentication;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public User registerUser(RegisterUserRequest request){
        // Check exist username in DB
        if(userRepository.existsByUsername(request.getUsername())){
            throw new CustomException("400","Username is already taken");
        }
        //Encode password
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        //Set role
        Role userRole = roleRepository.findRoleByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        return userRepository.save(new User(
                0,
                request.getFirstname(),
                request.getLastname(),
                request.getUsername(),
                encodedPassword,
                request.getPhone(),
                request.getFoodType(),
                request.getSex(),
                request.getAge(),
                request.getEmail(),
                request.getHometown(),
                authorities));
    }

    public LoginResponse loginUser(LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            String token = tokenService.generateJwt(auth);
            return new LoginResponse(userRepository.findUserByUsername(request.getUsername()), token);
        } catch (AuthenticationException e) {
            return new LoginResponse(null, "Error don't authentication and generate JWT");
        }
    }
}
