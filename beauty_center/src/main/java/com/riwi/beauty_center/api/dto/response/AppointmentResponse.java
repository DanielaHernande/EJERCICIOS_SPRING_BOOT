package com.riwi.beauty_center.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponse {

    private Long appointment_id;
    private LocalDateTime date_time;
    private int duration;
    private String comments;

    // Clientes
    private ClientToResponse client;

    // Empleados
    private EmployeeToResponse employee;

    // Servicios
    private ServiceToResponse service;
}
