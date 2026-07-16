package com.simaofpereira.task_manager_api.security;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

// Responsible for creating and validating JWT tokens
@Service
public class JwtService {

    // Secret key used to sign tokens (in a real production app, this should come
    // from
    // an environment variable, not be hardcoded)
    private final SecretKey key = Jwts.SIG.HS256.key().build();

    private final long expirationMs = 1000 * 60 * 60 * 10; // 10 hours

    // Generates a new JWT token containing the user's email
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key)
                .compact();
    }

    // Extracts the email (subject) from a given token
    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // Checks if the token is valid (not expired, correctly signed)
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}