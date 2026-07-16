package com.simaofpereira.task_manager_api.dto;

import lombok.Data;

// Represents the data needed to register a new user
@Data
public class RegisterRequest {
    private String email;
    private String password;
}