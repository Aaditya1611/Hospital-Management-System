package com.hms.covidData.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RoleFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String roleHeader = request.getHeader("X-Authenticated-Roles");
        String userHeader = request.getHeader("X-Authenticated-User");
        System.out.println("DEBUG: Received Roles Header: " + roleHeader);
        // if(roleHeader == null || !roleHeader.contains("ADMIN")) {

        // String user = request.getHeader("X-Authenticated-User");

        // response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        // response.getWriter().write("Access Denied: You must be an admin to access
        // this route");
        // return;
        // }
        if (roleHeader != null && !roleHeader.isEmpty()) {

            List<SimpleGrantedAuthority> authorities = Arrays.stream(roleHeader.split(","))
                    .map(role -> new SimpleGrantedAuthority(role.trim()))
                    .collect(Collectors.toList());

            String principal = (userHeader != null && !userHeader.isEmpty()) ? userHeader : "gateway_user";

            // Create token and place it in the SecurityContext
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal,
                    null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
