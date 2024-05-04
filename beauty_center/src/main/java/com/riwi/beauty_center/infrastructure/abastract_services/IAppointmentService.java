package com.riwi.beauty_center.infrastructure.abastract_services;

import com.riwi.beauty_center.api.dto.request.AppointmentRequest;
import com.riwi.beauty_center.api.dto.response.AppointmentResponse;

public interface IAppointmentService  extends  CrudService<AppointmentRequest, AppointmentResponse, Long>{

    
}
