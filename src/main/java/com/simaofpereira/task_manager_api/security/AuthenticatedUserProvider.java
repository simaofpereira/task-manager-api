package com.simaofpereira.task_manager_api.security;

import com.simaofpereira.task_manager_api.model.User;
import com.simaofpereira.task_manager_api.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

// Provides easy access to the currently authenticated user,
// based on the email stored in the security context by JwtAuthFilter
@Component
public class AuthenticatedUserProvider {

    private final UserRepository userRepository;

    public AuthenticatedUserProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));
    }
}