package com.simaofpereira.task_manager_api.repository;

import com.simaofpereira.task_manager_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query method: Spring Data JPA generates the SQL automatically
    // just from the method name (finds a user by their email)
    Optional<User> findByEmail(String email);
}