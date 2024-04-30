package com.riwi.beauty_center.api.dto.response;

import com.riwi.beauty_center.domain.entities.Appointment;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse {

    private Long service_id;
    private String name;
    private String description;
    private double price;

    private List<Appointment> appointment;
}
