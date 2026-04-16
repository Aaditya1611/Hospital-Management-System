package com.hms.doctor.helpdesk.repositories;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.doctor.helpdesk.entities.Appointments;

@Repository
public interface DoctorRepo extends JpaRepository<Appointments, Integer> {

    boolean findByAppointment(boolean appointment);

    LocalDateTime findByTime(LocalDateTime time);

    Appointments findByDoctorName(String name);

    Appointments findByUsernameAndTime(LocalDateTime time, String username);
    
}
