package com.project.reviewfood.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@AllArgsConstructor
@NoArgsConstructor
public class Post_Feed {
    @Id
    @Column(name = "post_feed_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postFeedId;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING, timezone = "GMT+7")
    private Date createDate;

    @OneToOne(mappedBy = "postFeed") //attribute of user class
    private Restaurant restaurant;

    @OneToMany(mappedBy = "postFeed") // is attribute of Comment class
    private List<Comment> comments;

    @OneToMany(mappedBy = "postFeed") // is attribute of Notification class
    private List<Notification> notifications;

    @OneToMany(mappedBy = "postFeed")
    private List<Like_Post_Feed> postFeedLikes;


}
