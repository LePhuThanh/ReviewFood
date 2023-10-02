package com.project.reviewfood.repositories;

import com.project.reviewfood.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByAuthority(String authority);
}
