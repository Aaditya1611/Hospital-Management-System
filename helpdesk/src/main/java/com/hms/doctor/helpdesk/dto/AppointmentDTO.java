package com.hms.doctor.helpdesk.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDTO {
    
    private String doctorname;
    private LocalDateTime time;
    private String username;

}
