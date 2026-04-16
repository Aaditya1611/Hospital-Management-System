package com.hms.doctor.helpdesk.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "appointments")
public class Appointments {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "doctor_name")
    private String doctorName;

    @Column (name = "specialised_in")
    private String specialisation;

    @Column (name = "experience_year")
    private int experience;

    @Column (name = "has_appointment")
    private boolean appointment;

    @Column (name = "appointment_time")
    private LocalDateTime time;

    @Column (name = "username")
    private String username;
}
