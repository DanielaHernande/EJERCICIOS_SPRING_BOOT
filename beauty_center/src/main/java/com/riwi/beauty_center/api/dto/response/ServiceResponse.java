package com.riwi.beauty_center.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse {

    private Long service_id;
    private String name;
    private String description;
    private double price;

    private List<AppointmentToResponse> appointment;
}
