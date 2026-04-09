package com.example.auth.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupResponseDTO {
    
    private Long id;
    private Long username;
}
