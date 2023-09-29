package com.project.reviewfood.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.reviewfood.entities.enums.StatusFriend;
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
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING, timezone = "GMT+7")
    @Column(name = "make_friend_date", nullable = true)
    private Date make_friend_date;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING, timezone = "GMT+7")
    @Column(name = "request_date", nullable = true)
    private Date request_date;

    //Reference ==> User table
    @ManyToOne(optional = false) // obligatory // User is required
    @JoinColumn(name = "sender_id",nullable = false)
    @JsonBackReference
    private User sender;

    //Reference ==> User table
    @ManyToOne(optional = false) // obligatory // User is required
    @JoinColumn(name = "receiver_id",nullable = false)
    @JsonBackReference
    private User receiver;

}
