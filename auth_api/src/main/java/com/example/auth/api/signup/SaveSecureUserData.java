package com.example.auth.api.signup;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth.api.signup.type.AuthProviderType;
import com.example.auth.api.signup.type.RoleType;

import jakarta.transaction.Transactional;

@Service
public class SaveSecureUserData {

    @Autowired
    public UserRepo userRepo;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public AdminRepo employeRepo;

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

    @Transactional
    public User saveAdmin(User request) throws Exception {

        String employeeId = request.getEmployeeId();
        Admin auth = employeRepo.findById(employeeId)
                .orElseThrow(() -> new Exception("This ID is not valid"));

        if (auth.isRegistered()) {
            throw new Exception("This ID is already in use.");

        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .providerId("None")
                .providerType(AuthProviderType.EMPLOYEE_SIGNUP)
                .roles(Set.of(RoleType.ADMIN))
                .build();

        auth.setRegistered(true);
        employeRepo.save(auth);
        return userRepo.save(user);
    }

}
