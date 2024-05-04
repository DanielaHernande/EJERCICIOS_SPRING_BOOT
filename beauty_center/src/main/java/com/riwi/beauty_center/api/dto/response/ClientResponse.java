package com.riwi.beauty_center.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

    private Long client_id;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;

    private List<AppointmentToResponse> appointment;
}
