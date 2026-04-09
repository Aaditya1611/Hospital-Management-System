package com.example.auth.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupRequestDTO {
    
    private String username;
    private String password;
   // private String name;
    private String email;
}
