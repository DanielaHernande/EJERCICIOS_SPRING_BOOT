package com.riwi.beauty_center.api.dto.response;

import com.riwi.beauty_center.domain.entities.Appointment;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

    private Long employee_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String role;

    private List<Appointment> appointment;
}
