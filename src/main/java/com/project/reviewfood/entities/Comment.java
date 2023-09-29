package com.project.reviewfood.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING, timezone = "GMT+7")
    private Date create_date;
    private String content;


    // Reference ==> User table
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    // Reference ==> Post_feed table
    @ManyToOne
    @JoinColumn(name = "post_feed_id", nullable = false)
    @JsonBackReference
    private Post_Feed postFeed;

    @OneToMany(mappedBy = "comment")
    @JsonManagedReference
    private List<Image> images;

    @OneToMany(mappedBy = "comment")
    @JsonManagedReference
    private List<Like_Comment> likeComments;


}
