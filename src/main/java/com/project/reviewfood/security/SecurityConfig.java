package com.project.reviewfood.security;

import com.project.reviewfood.services.DataInitializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import com.project.reviewfood.services.DataInitializationService;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationConverter jwtAuthenticationConverter;
    private final DataInitializationService dataInitializationService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/api/v1/auth/**").permitAll()
                                .requestMatchers("/api/v1/user/**").hasAnyRole("ADMIN","USER")
                                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                );
//                .httpBasic(Customizer.withDefaults())
//                .oauth2ResourceServer((oauth2)-> oauth2.jwt(Customizer.withDefaults()))
        http
                .oauth2ResourceServer((configurer )-> configurer
                        .jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter)
                        )
                );
        http
                .logout((logout)-> logout
                        .logoutUrl("/api/v1/auth/logout") // link users click logout
                        .logoutSuccessUrl("/api/v1/auth/login") // redirect login
                        .invalidateHttpSession(true) // cancel HTTP session when logout
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID") // Remove cookies related to session
                );


        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
    @Bean
    public CommandLineRunner run() {
        return args -> {
            dataInitializationService.initializeData();
        };
    }
}

//https://docs.spring.io/spring-security/site/docs/current/api/deprecated-list.html   => for is deprecated
