package com.example.auth.api.signup;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.example.auth.api.signup.type.BloodGroupType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    private User user;

    @Column(nullable = false, length = 49)
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    private LocalDate birthDate;

    private String gender;

    @CreationTimestamp
    @Column(updatable = false)
    private String createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroupType;

}
