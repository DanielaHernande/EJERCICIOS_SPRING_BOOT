package com.riwi.beauty_center.api.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ClientToResponse {

    private Long client_id;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;

}
