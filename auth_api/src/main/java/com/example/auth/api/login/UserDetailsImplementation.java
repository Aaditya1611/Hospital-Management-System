package com.example.auth.api.login;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.auth.api.signup.User;
import com.example.auth.api.signup.type.RoleType;

public class UserDetailsImplementation implements UserDetails {
    
    private final User user;
    private final Set<RoleType> roles;

    public UserDetailsImplementation(User user) {
        super();
        this.user = user;
        this.roles = user.getRoles();
    }

    public User getUser() {
        return this.user;
    }

    public Long getId() {
        return this.user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return roles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
            .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {

        return user.getPassword();
    }

    @Override
    public String getUsername() {

        return user.getUsername();
    }
}
