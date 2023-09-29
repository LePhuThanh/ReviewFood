package com.project.reviewfood.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.reviewfood.entities.enums.FoodType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @Column(name = "restaurant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;
    private String resName;
    private String resAddress;
    private String description;
    private Integer resPhone;
    private String resCountry;
    @Enumerated(EnumType.STRING)
    private FoodType resFoodType;

    // Reference ==> Post_Feed Table
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_feed_id", referencedColumnName = "post_feed_id") // at main id of post_feed
    @JsonBackReference
    private Post_Feed postFeed;

    @OneToMany(mappedBy = "restaurant") // is attribute of Rating class
    @JsonManagedReference
    private List<Rating> ratings;

}
