package com.riwi.beauty_center.infrastructure.abastract_services;

import com.riwi.beauty_center.api.dto.request.EmployeeRequest;
import com.riwi.beauty_center.api.dto.response.EmployeeResponse;

public interface IEmployeeService extends CrudService<EmployeeRequest, EmployeeResponse, Long>{
}
