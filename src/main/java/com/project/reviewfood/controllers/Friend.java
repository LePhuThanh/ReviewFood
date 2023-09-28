package com.project.reviewfood.controllers;

import com.project.reviewfood.controllers.enums.StatusFriend;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
    @Id
    @Column(name = "friend_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendId;
    @Enumerated(EnumType.STRING)
    private StatusFriend status;
    private Date make_friend_date;
    private Date request_date;

    //Reference ==> User table
    @ManyToOne(optional = false) // obligatory // User is required
    @JoinColumn(name = "sender_id",nullable = false)
    private User sender;

    //Reference ==> User table
    @ManyToOne(optional = false) // obligatory // User is required
    @JoinColumn(name = "receiver_id",nullable = false)
    private User receiver;

}
