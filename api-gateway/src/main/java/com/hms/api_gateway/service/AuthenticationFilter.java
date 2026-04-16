package com.hms.api_gateway.service;

import com.hms.api_gateway.utils.JwtUtil;

import io.jsonwebtoken.Claims;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();

            // 1. Skip validation for public endpoints and OPTIONS
            if (List.of("/auth/api/signup", "/auth/api/login").contains(path) ||
                    request.getMethod() == org.springframework.http.HttpMethod.OPTIONS) {
                return chain.filter(exchange);
            }

            // 2. Check for Authorization header
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String authHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String token = authHeader.substring(7);

            try {
                // 3. Validate Token Signature/Expiry
                if (!jwtUtil.validateToken(token)) {
                    throw new RuntimeException("Invalid Token");
                }

                String username = jwtUtil.extractUsername(token);

                Claims claims = jwtUtil.extractAllClaims(token);
                List<String> roles = claims.get("roles", List.class);

                // Example check for a specific route
                // if (path.startsWith("/hospitalManagementSystem/helpdesk/**") &&
                // !roles.contains("DOCTOR")) {
                // exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                // return exchange.getResponse().setComplete();
                // }

                // 4. Pass information to downstream microservices
                request = exchange.getRequest()
                        .mutate()
                        .header("X-Authenticated-User", username)
                        .header("X-Authenticated-Roles", String.join(",", roles))
                        .build();

                return chain.filter(exchange.mutate().request(request).build());

            } catch (Exception e) {
                System.out.println("Blocked invalid request: " + e.getMessage());
                // CRITICAL: Actually stop the request here
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
    }

    public static class Config {
        // put configuration properties here if you want to pass parameters from
        // application.yml
    }
}