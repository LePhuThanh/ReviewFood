package com.project.reviewfood.controllers;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Likes {
    @Id
    @Column(name = "like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;
    private Date createDate;

    // Reference ==> User table
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Reference ==> Post_Feed table
    @ManyToOne
    @JoinColumn(name = "post_feed_id", nullable = false)
    private Post_Feed postFeed;

    // Reference ==> Comment table
    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;
}
