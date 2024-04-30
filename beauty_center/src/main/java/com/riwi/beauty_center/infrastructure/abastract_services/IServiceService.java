package com.riwi.beauty_center.infrastructure.abastract_services;

import com.riwi.beauty_center.api.dto.request.ServiceRequest;
import com.riwi.beauty_center.api.dto.response.ServiceResponse;

public interface IServiceService extends CrudService<ServiceRequest, ServiceResponse, Long>{
}
