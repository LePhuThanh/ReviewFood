package com.project.reviewfood.payloads.requests;


import com.project.reviewfood.entities.enums.FoodType;
import com.project.reviewfood.entities.enums.Sex;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    @Size(min = 2,max = 300)
    private String firstname; // Tên
    @Size(min = 2,max = 300)
    private String lastname; // Họ
    private String phone;
    @Enumerated(EnumType.STRING)
    private FoodType foodType; //favourite the type of food
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Min(1)
    @Max(150)
    private Integer age;
    private String email;
    private String hometown;
}
