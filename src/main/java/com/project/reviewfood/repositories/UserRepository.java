package com.project.reviewfood.repositories;

import com.project.reviewfood.entities.User;
import com.project.reviewfood.entities.enums.FoodType;
import com.project.reviewfood.entities.enums.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserName(String userName);
    User findUserByUserId(Long userId);
    User findUserByPhone(Integer phone);
    List<User> findUserBySex(Sex sex);
    List<User> findUserByAge(Integer age);

    @Query(value = "SELECT u FROM User u WHERE u.favouriteFoodType = :foodType",nativeQuery = true) //JPQL (Java Persistence Query Language)
    List<User> findUserByFavouriteFoodType(@Param("foodType") String foodType);
}
