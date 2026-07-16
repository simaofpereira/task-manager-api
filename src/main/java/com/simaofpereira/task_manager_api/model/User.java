package com.simaofpereira.task_manager_api.model;

import jakarta.persistence.*;
import lombok.Data;

// Represents an application user, mapped to the "users" table
@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // will be stored encrypted, never in plain text
}