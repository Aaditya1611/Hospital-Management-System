package com.example.auth.api.signup;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth.api.signup.type.AuthProviderType;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    
    User findByUsername(String username);

    User findByEmail(String email);

    Optional<User> findByProviderIdAndProviderType(String providerId, AuthProviderType providerType);
}
