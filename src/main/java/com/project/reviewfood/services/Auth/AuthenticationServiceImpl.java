package com.project.reviewfood.services.Auth;
import com.project.reviewfood.entities.Role;
import com.project.reviewfood.entities.User;
import com.project.reviewfood.handlers.CustomException;
import com.project.reviewfood.payloads.requests.LoginRequest;
import com.project.reviewfood.payloads.requests.RegisterUserRequest;
import com.project.reviewfood.repositories.RoleRepository;
import com.project.reviewfood.repositories.UserRepository;
import com.project.reviewfood.services.Impl.UserServiceImpl;
import com.project.reviewfood.util.EmailUtil;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
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
    private EmailUtil emailUtil;
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
    @Autowired
    private UserServiceImpl userServiceIml;


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

//    public LoginResponse loginUser(LoginRequest request) {
//        try {
//            Authentication auth = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//            // UsernamePasswordAuthenticationToken save inf authenticated user in SecurityContext
//            // SecurityContextHolder provide method for SecurityContext
//            String token = tokenService.generateJwt(auth);
//            UserDetails userDetails = userServiceIml.loadUserByUsername(request.getUsername());
////            System.out.println("DK " + tokenService.isTokenValid(token, userDetails));
//            if(tokenService.isTokenValid(token, userDetails)){
//                return new LoginResponse(userRepository.findUserByUsername(request.getUsername()), token);
////                return new LoginResponse(userDetails, token);
//            }else {
//                return new LoginResponse(null, "Invalid token. Please log in again.");
//            }
//        } catch (AuthenticationException e) {
//            return new LoginResponse(null, "There was an error authenticating and generating JWT, please log in again.");
//        }
//    }
    public LoginResponse loginUser(LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            String token = tokenService.generateJwt(auth);
            return new LoginResponse(userRepository.findUserByUsername(request.getUsername()), token);
        } catch (AuthenticationException e) {
            return new LoginResponse(null, "There was an error authenticating and generating JWT, please log in again.");
        }
    }
    //========================================================================
    @Override
    public Boolean forgotPassword(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new CustomException("404","User not found with this email: " + email));
        try {
            emailUtil.sendResetPasswordEmail(email);
        } catch (MessagingException e){
            throw new CustomException("500","Unable to send set password email please try again!");
        }
        return true;
    }

    @Override
    public Boolean setNewPassword(String email, String newPassword) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new CustomException("404","User not found with this email: " + email));
        //Encode Password
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return true;
    }
}
