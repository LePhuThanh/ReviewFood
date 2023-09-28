package com.project.reviewfood.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.reviewfood.controllers.enums.FoodType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstname;
    private String lastname;
    private String userName;
    @JsonIgnore
    private String password;
    private Integer phone;
    @Enumerated(EnumType.STRING)
    private FoodType foodType; //favourite the type of food
    private Integer age;
    private String email;
    private String hometown;

    @OneToMany(mappedBy = "user") // is attribute of Comment class
    private List<Comment> comments;

    @OneToMany(mappedBy = "sender") // is attribute of Friend class
    private List<Friend> senders;

    @OneToMany(mappedBy = "receiver") // is attribute of Friend class
    private List<Friend> receivers;

    @OneToMany(mappedBy = "user") // is attribute of Notification class
    private List<Notification> notifications;

    @OneToMany(mappedBy = "follower") // is attribute of Follow class
    private List<Follow> followers;

    @OneToMany(mappedBy = "following") // is attribute of Follow class
    private List<Follow> followings;

    @OneToMany(mappedBy = "user") // is attribute of Like class
    private List<Likes> likes;

    @OneToMany(mappedBy = "personPost") // is attribute of Share_Post class
    private List<Share_Post> personPosts;

    @OneToMany(mappedBy = "personShare") // is attribute of Share_Post class
    private List<Share_Post> personShares;
}
