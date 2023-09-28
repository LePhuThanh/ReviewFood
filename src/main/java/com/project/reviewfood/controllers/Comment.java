package com.project.reviewfood.controllers;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private Date create_date;
    private String content;

    // Reference ==> User table
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Reference ==> Post_feed table
    @ManyToOne
    @JoinColumn(name = "post_feed_id", nullable = false)
    private Post_Feed postFeed;

    @OneToMany(mappedBy = "comment")
    private List<Image> images;

    @OneToMany(mappedBy = "comment")
    private List<Likes> likes;

}
