package com.riwi.beauty_center.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeToResponse {
    
    private Long employee_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String role;
}
