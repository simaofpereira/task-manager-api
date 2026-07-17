package com.simaofpereira.task_manager_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// A simple public endpoint to confirm the API is running,
// useful for checking the deployment from a browser
@RestController
public class HealthController {

    @GetMapping("/")
    public String home() {
        return "Task Manager API is running! Check /api/auth/register or /api/auth/login to get started.";
    }
}