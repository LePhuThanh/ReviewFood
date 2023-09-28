package com.project.reviewfood.controllers;

import com.project.reviewfood.controllers.enums.RatingNumber;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    @Column(name = "rating_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;
    private Long userId;
    private Date ratingDate;
    @Enumerated(EnumType.STRING)
    private RatingNumber rating;

    //Reference ==> Restaurant table
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

}
