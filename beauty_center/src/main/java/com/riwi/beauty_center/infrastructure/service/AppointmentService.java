package com.riwi.beauty_center.infrastructure.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.beauty_center.api.dto.request.AppointmentRequest;
import com.riwi.beauty_center.api.dto.response.AppointmentResponse;
import com.riwi.beauty_center.api.dto.response.ClientToResponse;
import com.riwi.beauty_center.api.dto.response.EmployeeToResponse;
import com.riwi.beauty_center.api.dto.response.ServiceToResponse;
import com.riwi.beauty_center.domain.entities.Appointment;
import com.riwi.beauty_center.domain.entities.Client;
import com.riwi.beauty_center.domain.entities.Employee;
import com.riwi.beauty_center.domain.entities.Servicio;
import com.riwi.beauty_center.domain.repositories.AppointmentRepository;
import com.riwi.beauty_center.domain.repositories.ClientRepository;
import com.riwi.beauty_center.domain.repositories.EmployeeRepository;
import com.riwi.beauty_center.domain.repositories.ServiceRepository;
import com.riwi.beauty_center.infrastructure.abastract_services.IAppointmentService;
import com.riwi.beauty_center.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppointmentService implements IAppointmentService{

    // Repositorio de cita
    @Autowired
    public final AppointmentRepository appointmentRepository;

    // Repositorio de cliente
    @Autowired
    public final ClientRepository clientRepository;

    // Respositorio de empleado
    @Autowired
    public final EmployeeRepository employeeRepository;

    // Repositorio de servicio
    @Autowired
    public final ServiceRepository serviceRepository;

    // Listar
    @Override
    public Page<AppointmentResponse> getAll(int page, int size) {

        if (page < 0) page = 0;

        PageRequest paginatio = PageRequest.of(page, size);

        return this.appointmentRepository.findAll(paginatio)
                .map(this::entityToResponse);
    }

    // Crear
    @Override
    public AppointmentResponse create(AppointmentRequest request) {

        // Miramos si el cliente, empleados y servicios estan en la bd
        Client client = this.clientRepository.findById(request.getClient().getClient_id())
                        .orElseThrow(() -> new IdNotFoundException("Client"));

        Employee employee = this.employeeRepository.findById(request.getEmployee().getEmployee_id())
                            .orElseThrow(() -> new IdNotFoundException("Employee"));

        Servicio servicio = this.serviceRepository.findById(request.getService().getService_id())
                            .orElseThrow(() -> new IdNotFoundException("Servicio"));

        Appointment appointment = this.requestToEntity(request, new Appointment());

        appointment.setClient(client);
        appointment.setEmployee(employee);
        appointment.setService(servicio);
        
        return this.entityToResponse(this.appointmentRepository.save(appointment));
    }

    // Actualizar
    @Override
    public AppointmentResponse update(AppointmentRequest request, Long id) {

        Appointment appointment = this.find(id);

        Client client = this.clientRepository.findById(request.getClient().getClient_id())
                        .orElseThrow(() -> new IdNotFoundException("Client"));

        Employee employee = this.employeeRepository.findById(request.getEmployee().getEmployee_id())
                        .orElseThrow(() -> new IdNotFoundException("Employee"));

        Servicio servicio = this.serviceRepository.findById(request.getService().getService_id())
                        .orElseThrow(() -> new IdNotFoundException("Servicio"));

        appointment.setClient(client);
        appointment.setEmployee(employee);
        appointment.setService(servicio);
        
        return this.entityToResponse(this.appointmentRepository.save(appointment));
    }

    // Eliminar
    @Override
    public void delete(Long id) {

        Appointment appointment = this.find(id);

        this.appointmentRepository.delete(appointment);
    }

    // Buscar por Id
    @Override
    public AppointmentResponse getById(Long id) {

        return this.entityToResponse(find(id));
    }

    // Metodos Privados
    private AppointmentResponse entityToResponse(Appointment appointment) {

        // Crear una instancia
        AppointmentResponse response = new AppointmentResponse();

        // Copiamos las propiedades de la entidad al dto de respuesta
        BeanUtils.copyProperties(appointment, response);

        // Crear la instancia de dto de cliente dentro de cita
        ClientToResponse clientResp = new ClientToResponse();

        // Crear la instancia de dto de empleado dentro de cita
        EmployeeToResponse employeeResp = new EmployeeToResponse();

        // Crear la instancia de dto de servicio dentro de cita
        ServiceToResponse serviceResp = new ServiceToResponse();

        // Copio las propiedades de la entidad en el dto de respuesta
        BeanUtils.copyProperties(appointment.getClient(), clientResp);
        BeanUtils.copyProperties(appointment.getEmployee(), employeeResp);
        BeanUtils.copyProperties(appointment.getService(), serviceResp);

        response.setClient(clientResp);
        response.setEmployee(employeeResp);
        response.setService(serviceResp);

        return response;
    }

    private Appointment requestToEntity(AppointmentRequest request, Appointment entity) {

        entity.setDate_time(request.getDate_time());
        entity.setDuration(request.getDuration());
        entity.setComments(request.getComments());

        return entity;
    }

    private Appointment find(Long id) {

        return this.appointmentRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Appointment"));
    }

}
