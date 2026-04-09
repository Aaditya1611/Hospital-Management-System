package com.hms.api_gateway.utils;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

    String privateKey = "keyWzA21AHKb8NcC7STopUjHeA9SmkoKvi6Q2GIcYxS1Sc";

    public SecretKey getKey() {
        byte[] keyByte = Decoders.BASE64.decode(privateKey);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public void validateToken(final String token) {
        
        Jwts.parser()
            .verifyWith(getKey())
            .build()
            .parseSignedClaims(token); 
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
