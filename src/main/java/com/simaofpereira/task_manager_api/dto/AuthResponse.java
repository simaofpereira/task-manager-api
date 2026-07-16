package com.simaofpereira.task_manager_api.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

// Represents the response sent back after a successful login/register: just the JWT token
@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
}