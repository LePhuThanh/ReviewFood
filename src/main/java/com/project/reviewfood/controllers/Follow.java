package com.project.reviewfood.controllers;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
    @Id
    @Column(name = "follow_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    //Reference ==> User table
    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;
    //Reference ==> User table
    @ManyToOne
    @JoinColumn(name = "followed_user_id", nullable = false)
    private User followedUser;
}
