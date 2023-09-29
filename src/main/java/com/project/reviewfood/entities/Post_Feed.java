package com.project.reviewfood.entities;

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
    private Long shared_post_id;

    // Reference ==> User table
    @ManyToOne
    @JoinColumn(name = "person_post_id", nullable = false)
    private User personPost;

    @OneToOne(mappedBy = "postFeed") //attribute of user class
    private Restaurant restaurant;

    @OneToMany(mappedBy = "postFeed") // is attribute of Comment class
    private List<Comment> comments;

    @OneToMany(mappedBy = "postFeed")  // is attribute of Like_Post_Feed class
    private List<Like_Post_Feed> postFeedLikes;


}
