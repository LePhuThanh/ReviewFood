package com.project.reviewfood.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.reviewfood.entities.enums.FoodType;
import com.project.reviewfood.entities.enums.Sex;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User  {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "first_name", nullable = false)
    @Size(min = 2,max = 300)
    private String firstname; // Tên
    @Column(name = "last_name", nullable = false)
    @Size(min = 2,max = 300)
    private String lastname; // Họ

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    private String phone;
    @Enumerated(EnumType.STRING)
    private FoodType foodType; //favourite the type of food
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Min(1)
    @Max(150)
    private Integer age;
    private String email;
    private String hometown;

    //Email Verification
    private String otp;
    private Boolean activeEmail;
    private LocalDateTime otpGenerateTime;

//    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_function",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> authorities;

    @JsonIgnore
    @OneToMany(mappedBy = "user") // is attribute of Comment class
    @JsonManagedReference
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "sender") // is attribute of Friend class
    @JsonManagedReference
    private List<Friend> senders;

    @JsonIgnore
    @OneToMany(mappedBy = "receiver") // is attribute of Friend class
    @JsonManagedReference
    private List<Friend> receivers;

    @JsonIgnore
    @OneToMany(mappedBy = "user") // is attribute of Notification class
    @JsonManagedReference
    private List<Notification> notifications;

    @JsonIgnore
    @OneToMany(mappedBy = "follower") // is attribute of Follow class
    @JsonManagedReference
    private List<Follow> followers;

    @JsonIgnore
    @OneToMany(mappedBy = "followedUser") // is attribute of Follow class
    @JsonManagedReference
    private List<Follow> followedUsers;

    @JsonIgnore
    @OneToMany(mappedBy = "user") // is attribute of Like_Post_Feed class
    @JsonManagedReference
    private List<Like_Post_Feed> postFeedLikes;

    @JsonIgnore
    @OneToMany(mappedBy = "user") // is attribute of Like_Comment class
    @JsonManagedReference
    private List<Like_Comment> commentLikes;

    @JsonIgnore
    @OneToMany(mappedBy = "personPost") // is attribute of Post_Feed class
    @JsonManagedReference
    private List<Post_Feed> personPosts;

    public User(int i, String firstname, String lastname, String username,
                String encodedPassword, String phone, FoodType foodType,
                Sex sex, Integer age, String email, String hometown,
                Set<Role> authorities) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = encodedPassword;
        this.phone = phone;
        this.foodType = foodType;
        this.sex = sex;
        this.age = age;
        this.email = email;
        this.hometown = hometown;
        this.authorities = authorities;
    }



    //https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion

}
