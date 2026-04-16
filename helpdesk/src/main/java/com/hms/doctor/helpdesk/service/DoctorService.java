package com.hms.doctor.helpdesk.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hms.doctor.helpdesk.entities.Appointments;
import com.hms.doctor.helpdesk.repositories.DoctorRepo;

@Service
@Transactional
public class DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    public boolean getAppointment(String doctorName, LocalDateTime time, String username) {

        Appointments schedule = doctorRepo.findByDoctorName(doctorName);

        if (schedule == null) {
            return true;
        }
        if (time.equals(schedule.getTime()) && schedule.isAppointment()) {
            return false;
        } else {
            schedule.setAppointment(true);
            schedule.setTime(time);
            schedule.setUsername(username);
            doctorRepo.save(schedule);
        }
        return true;
    }

    public boolean cancelAppointmet(String doctorName, LocalDateTime time, String username) {

        Appointments appointmet = doctorRepo.findByUsernameAndTime(time, username);
        
        if(appointmet != null) {
            appointmet.setAppointment(false);
            appointmet.setTime(null);
            appointmet.setUsername(null);
        }
        return true;
    }
}
