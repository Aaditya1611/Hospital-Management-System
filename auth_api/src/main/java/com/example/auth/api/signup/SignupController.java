package com.example.auth.api.signup;

import org.springframework.web.bind.annotation.RestController;

import com.example.auth.api.jwt.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class SignupController {

    @Autowired
    private SaveSecureUserData saveSecureUserData;

    @Autowired
    JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        try {
            saveSecureUserData.saveUserData(user);
            return ResponseEntity.ok().body("User registered successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body("Registration failed");
        }
    }

    @PostMapping("/signupAdmin")
    public ResponseEntity<?> registerAdmin(@RequestBody User user) {

        try {
            saveSecureUserData.saveAdmin(user);
            return ResponseEntity.ok().body("Admin registered successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body("Registration failed");
        }
    }
    

}
