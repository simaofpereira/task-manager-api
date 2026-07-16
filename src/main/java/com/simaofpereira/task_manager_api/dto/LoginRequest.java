package com.simaofpereira.task_manager_api.dto;

import lombok.Data;

// Represents the data needed to log in
@Data
public class LoginRequest {
    private String email;
    private String password;
}