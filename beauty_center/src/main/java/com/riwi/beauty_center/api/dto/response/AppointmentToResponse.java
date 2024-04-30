package com.riwi.beauty_center.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentToResponse {

    private Long appointment_id;
    private LocalDateTime date_time;
    private int duration;
    private String comments;
}
