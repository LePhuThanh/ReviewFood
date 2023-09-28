package com.project.reviewfood.controllers;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @Column(name = "notif_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notifId;
    private Date notifDate;
    private Boolean isRead;

    //Reference ==> User table
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //Reference ==> Post_feed table
    @ManyToOne
    @JoinColumn(name = "post_feed_id", nullable = false)
    private Post_Feed postFeed;

}
