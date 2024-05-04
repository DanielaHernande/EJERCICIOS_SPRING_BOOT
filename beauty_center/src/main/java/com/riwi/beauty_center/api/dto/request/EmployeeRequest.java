package com.riwi.beauty_center.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String role;
}
