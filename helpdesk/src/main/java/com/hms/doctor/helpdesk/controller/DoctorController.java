package com.hms.doctor.helpdesk.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.doctor.helpdesk.dto.AppointmentDTO;
import com.hms.doctor.helpdesk.service.DoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    
    @PostMapping("/getappointment")
    public ResponseEntity<?> BookAppointment(AppointmentDTO appointmentDTO) {

        boolean appointment = doctorService.getAppointment(appointmentDTO.getDoctorname(), appointmentDTO.getTime(), appointmentDTO.getUsername());
        if (appointment == true) {
            return ResponseEntity.ok("Appointment successful");
        }
        else return ResponseEntity.status(403).body("Already occupied");
        
    }

    @PostMapping("/cancelappointmet")
    public ResponseEntity<?> CancelAppointment(AppointmentDTO appointmentDTO) {

        boolean appointment = doctorService.cancelAppointmet(appointmentDTO.getDoctorname(), appointmentDTO.getTime(), appointmentDTO.getUsername());
        if (appointment == true) {
            return ResponseEntity.ok("Appointment has been cancelled");
        } 
        else return ResponseEntity.status(403).body("Failed to cancel the appointment");
    }
}
