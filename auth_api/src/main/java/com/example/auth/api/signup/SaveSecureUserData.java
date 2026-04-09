package com.example.auth.api.signup;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth.api.signup.type.AuthProviderType;
import com.example.auth.api.signup.type.RoleType;

@Service
public class SaveSecureUserData {
    
    @Autowired
    public UserRepo userRepo;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public void saveUserData(User user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
         User newUser = User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(encodedPassword)
                .providerId("None")
                .providerType(AuthProviderType.BASIC)
                .roles(Set.of(RoleType.PATIENT))
                .build();
        userRepo.save(newUser);
    }
}
