package com.jaypal.journalApp.utilis;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtUtil is a utility class responsible for:
 * - Generating JWT tokens
 * - Validating and parsing JWT tokens
 * - Extracting claims such as username and expiration time
 */
@Component
public class JwtUtil {

    // ⚠️ WARNING: Move this key to environment variables or secure vault in production
    private final String SECRET_KEY = "3Vhi9ZCNLWzzYBtO0IMbAokKy/Zh+ORzT4fsGbN0IAY=";

    /**
     * Generates the signing key used to sign/verify the JWT.
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    /**
     * Extracts the username (subject) from the JWT token.
     */
    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    /**
     * Extracts the expiration date from the JWT token.
     */
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    /**
     * Parses and retrieves all claims from the JWT token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Checks if the token is expired by comparing its expiration date with the current time.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Generates a JWT token for the given username (used as the subject).
     * Optionally, custom claims can be added to the token.
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    /**
     * Builds the JWT token with claims, subject, issue time, expiration, and signs it.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)                        // Custom payload
                .subject(subject)                      // Typically the username
                .header().empty().add("typ", "JWT")    // Set token type in header
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))              // Token issue time
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60)) // Expiry in 1 minutes
                .signWith(getSigningKey())             // Sign with secret key
                .compact();                            // Final JWT token string
    }

    /**
     * Validates the token by ensuring it has not expired.
     * (Additional validation like signature verification is already done by the parser)
     */
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
