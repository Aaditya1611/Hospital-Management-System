package com.hms.api_gateway.utils;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

    String privateKey = "keyWzA21AHKb8NcC7STopUjHeA9SmkoKvi6Q2GIcYxS1Scfdsfe23esfasdffWSEWwfw3dfsa";

    public SecretKey getKey() {

        byte[] keyByte = Decoders.BASE64.decode(privateKey);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String extractUsername(String token) {

        return extractClaims(token, Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token) {

        try {
            Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {

        return extractClaims(token, Claims::getExpiration);
    }
}
