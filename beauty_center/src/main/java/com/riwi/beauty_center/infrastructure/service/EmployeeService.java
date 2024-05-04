package com.riwi.beauty_center.infrastructure.service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.beauty_center.api.dto.request.EmployeeRequest;
import com.riwi.beauty_center.api.dto.response.AppointmentToResponse;
import com.riwi.beauty_center.api.dto.response.EmployeeResponse;
import com.riwi.beauty_center.domain.entities.Appointment;
import com.riwi.beauty_center.domain.entities.Employee;
import com.riwi.beauty_center.domain.repositories.EmployeeRepository;
import com.riwi.beauty_center.infrastructure.abastract_services.IEmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService implements IEmployeeService{

    @Autowired
    public final EmployeeRepository employeeRepository;

    // Listar los empleados
    @Override
    public Page<EmployeeResponse> getAll(int page, int size) {

        if (page < 0)  page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.employeeRepository.findAll(pagination)
                .map(this::entityToResponse);
        
    }

    // Crear 
    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        
        Employee employee = this.requestToEntity(request, new Employee());

        return this.entityToResponse(this.employeeRepository.save(employee));
    }

    // Actualizar
    @Override
    public EmployeeResponse update(EmployeeRequest request, Long id) {

        Employee employee = this.find(id);

        Employee employeeUpdate = this.requestToEntity(request, employee);

        return this.entityToResponse(this.employeeRepository.save(employeeUpdate));
    }

    // Eliminar
    @Override
    public void delete(Long id) {

        Employee employee = this.find(id);

        this.employeeRepository.delete(employee);
    }

    // Buscar por Id
    @Override
    public EmployeeResponse getById(Long id) {

        return this.entityToResponse(this.find(id));
    }


    // Este metodo se encarga de convertir una entidad a dto de respuesta
    private EmployeeResponse entityToResponse(Employee entity) {

        EmployeeResponse response = new EmployeeResponse();

        BeanUtils.copyProperties(entity, response);

        response.setAppointment(entity.getAppointment().stream()
        .map(this::appointmentToResponse)
        .collect(Collectors.toList()));

        return response;
    }

    private AppointmentToResponse appointmentToResponse(Appointment enity) {

        AppointmentToResponse response = new AppointmentToResponse();

        BeanUtils.copyProperties(enity, response);

        return response;
    }

    private Employee requestToEntity(EmployeeRequest request, Employee employee) {

        employee.setFirst_name(request.getFirst_name());
        employee.setLast_name(request.getLast_name());
        employee.setPhone(request.getPhone());
        employee.setEmail(request.getEmail());
        employee.setRole(request.getRole());
        employee.setAppointment(new ArrayList<>());

        return employee;
    }

    private Employee find(Long id) {

        return this.employeeRepository.findById(id).orElseThrow();
    }


}
