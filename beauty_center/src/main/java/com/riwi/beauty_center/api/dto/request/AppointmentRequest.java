package com.riwi.beauty_center.api.dto.request;

import com.riwi.beauty_center.domain.entities.Client;
import com.riwi.beauty_center.domain.entities.Employee;
import com.riwi.beauty_center.domain.entities.Service;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest {

    private LocalDateTime date_time;
    private int duration;
    private String comments;

    // Clientes
    private Client client;

    // Empleados
    private Employee employee;

    // Servicios
    private Service service;
}
