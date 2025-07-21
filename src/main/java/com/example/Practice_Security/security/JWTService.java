package com.example.Practice_Security.security;

import com.example.Practice_Security.utils.SecretKeyGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {
    private final String secretKey = SecretKeyGenerator.generateSecretKey();

    public JWTService() throws NoSuchAlgorithmException {
    }

    public String generateToken(String email){
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() * 36000))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    private Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    private Date getExpiration(String token){
        return getClaims(token).getExpiration();
    }

    private boolean isExpired(String token){
        return getExpiration(token).before(new Date());
    }
    public boolean validateToken(String token, UserDetails userDetails) {
        return getUsername(token).equals(userDetails.getUsername()) && !isExpired(token);
    }
}
