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
public class Share_Post {
    @Id
    @Column(name = "share_post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sharePostId;

    // Reference Post_Feed table
    @ManyToOne
    @JoinColumn(name = "post_feed_id", nullable = false)
    private Post_Feed postFeed;

    // Reference User table
    @ManyToOne
    @JoinColumn(name = "person_post_id", nullable = false)
    private User personPost;

    // Reference User table
    @ManyToOne
    @JoinColumn(name = "person_share_id", nullable = false)
    private User personShare;

}
