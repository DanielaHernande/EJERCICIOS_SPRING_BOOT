package com.riwi.beauty_center.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceToResponse {
    
    private Long service_id;
    private String name;
    private String description;
    private double price;
}
