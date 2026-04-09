package com.example.auth.api.signup;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Long>{
    

}
