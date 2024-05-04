package com.riwi.beauty_center.infrastructure.service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.beauty_center.domain.entities.Appointment;
import com.riwi.beauty_center.domain.entities.Servicio;
import com.riwi.beauty_center.api.dto.request.ServiceRequest;
import com.riwi.beauty_center.api.dto.response.AppointmentToResponse;
import com.riwi.beauty_center.api.dto.response.ServiceResponse;
import com.riwi.beauty_center.domain.repositories.ServiceRepository;
import com.riwi.beauty_center.infrastructure.abastract_services.IServiceService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SerciceService implements IServiceService {

    @Autowired
    private final ServiceRepository serviceRepository;

    // Listar
    @Override
    public Page<ServiceResponse> getAll(int page, int size) {
        
        if (page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.serviceRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    // Crear
    @Override
    public ServiceResponse create(ServiceRequest request) {

        Servicio servicio = this.requestToEntity(request, new Servicio());

        return this.entityToResponse(this.serviceRepository.save(servicio));
    }

    // Actualizar
    @Override
    public ServiceResponse update(ServiceRequest request, Long id) {

        Servicio servicio = this.find(id);

        Servicio servicioUpdate = this.requestToEntity(request, servicio);

        return this.entityToResponse(this.serviceRepository.save(servicioUpdate));
    }

    // Eliminar
    @Override
    public void delete(Long id) {

        Servicio servicio = this.find(id);

        this.serviceRepository.delete(servicio);
    }

    // Buscar por id
    @Override
    public ServiceResponse getById(Long id) {

        return this.entityToResponse(this.find(id));
    }

    // Metodos privados
    private ServiceResponse entityToResponse(Servicio entity) {

        ServiceResponse response = new ServiceResponse();

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

    private Servicio requestToEntity(ServiceRequest request, Servicio servicio) {

        servicio.setName(request.getName());
        servicio.setDescription(request.getDescription());
        servicio.setPrice(request.getPrice());
        servicio.setAppointment(new ArrayList<>());

        return servicio;
    }

    private Servicio find(Long id) {
        return this.serviceRepository.findById(id).orElseThrow();

    }
    
}