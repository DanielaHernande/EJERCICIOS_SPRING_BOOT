package com.riwi.beauty_center.api.dto.response;

import com.riwi.beauty_center.domain.entities.Appointment;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

    private Long client_id;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;

    private List<Appointment> appointment;
}
