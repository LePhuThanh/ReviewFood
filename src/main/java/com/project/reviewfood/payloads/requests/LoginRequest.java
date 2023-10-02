package com.project.reviewfood.payloads.requests;

import jakarta.persistence.Column;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NonNull
    @Column(nullable = false, unique = true)
    private String username;
    @NonNull
    private String password;
}
