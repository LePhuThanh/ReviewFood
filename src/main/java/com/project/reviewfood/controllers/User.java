package com.project.reviewfood.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.reviewfood.controllers.enums.FoodType;
import com.project.reviewfood.controllers.enums.Sex;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
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
    @Column(name = "first_name", nullable = false)
    @Size(min = 2,max = 300)
    private String firstname; // Tên
    @Column(name = "last_name", nullable = false)
    @Size(min = 2,max = 300)
    private String lastname; // Họ
    private String userName;
    @JsonIgnore
    private String password;
    private Integer phone;
    @Enumerated(EnumType.STRING)
    private FoodType foodType; //favourite the type of food
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Min(1)
    @Max(150)
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

    @OneToMany(mappedBy = "followedUser") // is attribute of Follow class
    private List<Follow> followedUsers;

    @OneToMany(mappedBy = "user") // is attribute of Like_Post_Feed class
    private List<Like_Post_Feed> postFeedLikes;

    @OneToMany(mappedBy = "user") // is attribute of Like_Comment class
    private List<Like_Comment> commentLikes;

    @OneToMany(mappedBy = "personPost") // is attribute of Share_Post class
    private List<Share_Post> personPosts;

    @OneToMany(mappedBy = "personShare") // is attribute of Share_Post class
    private List<Share_Post> personShares;
}
