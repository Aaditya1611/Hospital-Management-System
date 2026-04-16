package com.example.auth.api.signup;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "authorized_employees")
@Getter
@Setter
public class Admin {
    
    @Id
    private String employeeId;
    private String email;      
    private boolean isRegistered; 

}
