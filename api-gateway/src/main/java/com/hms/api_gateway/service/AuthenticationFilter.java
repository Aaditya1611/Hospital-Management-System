package com.hms.api_gateway.service;

import com.hms.api_gateway.utils.JwtUtil;

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
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                System.err.println(exchange + "Invalid authorization header format" + HttpStatus.UNAUTHORIZED);
            }

            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            
            String authHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                authHeader = authHeader.substring(7);
            } else {
                System.err.println(exchange + "Invalid authorization header format" + HttpStatus.UNAUTHORIZED);
            }

            try {
                jwtUtil.validateToken(authHeader);

                String username = jwtUtil.extractUsername(authHeader);

                request = exchange.getRequest()
                        .mutate()
                        .header("X-Authenticated-User", username)
                        .build();

            } catch (Exception e) {
                System.out.println("Invalid access... " + e.getMessage());
                // return this.onError(exchange, "Invalid JWT token", HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    // // Helper method to handle errors reactively
    // private reactor.core.publisher.Mono<Void>
    // onError(org.springframework.web.server.ServerWebExchange exchange,
    // String err, HttpStatus httpStatus) {
    // ServerHttpResponse response = exchange.getResponse();
    // response.setStatusCode(httpStatus);
    // return response.setComplete();
    // }

    public static class Config {
        // put configuration properties here if you want to pass parameters from
        // application.yml
    }
}