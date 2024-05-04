package com.riwi.beauty_center.infrastructure.service;

import com.riwi.beauty_center.api.dto.request.ClientRequest;
import com.riwi.beauty_center.api.dto.response.AppointmentToResponse;
import com.riwi.beauty_center.api.dto.response.ClientResponse;
import com.riwi.beauty_center.domain.entities.Appointment;
import com.riwi.beauty_center.domain.entities.Client;
import com.riwi.beauty_center.domain.repositories.ClientRepository;
import com.riwi.beauty_center.infrastructure.abastract_services.IClientService;
import com.riwi.beauty_center.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService implements IClientService {

    @Autowired
    private final ClientRepository clientRepository;

    // Listar
    @Override
    public Page<ClientResponse> getAll(int page, int size) {

        if (page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.clientRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    // Crear
    @Override
    public ClientResponse create(ClientRequest request) {
        
        Client client = this.requestToEntity(request, new Client());

        return this.entityToResponse(this.clientRepository.save(client));
    }

    // Actualizar
    @Override
    public ClientResponse update(ClientRequest request, Long id) {

        Client client = this.find(id);

        Client clientUpdate = this.requestToEntity(request, client);

        return this.entityToResponse(this.clientRepository.save(clientUpdate));
        
    }

    // Eliminar 
    @Override
    public void delete(Long id) {

        Client client = this.find(id);

        this.clientRepository.delete(client);
    }

    // Buscar por Id
    @Override
    public ClientResponse getById(Long aLong) {
        
        return this.entityToResponse(this.find(aLong));
    }

    // Metodos privados

    // Este metodo se encarga de convertir una entidad a dto de respuesta
    private ClientResponse entityToResponse(Client entity) {

        ClientResponse response = new ClientResponse();

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

    // copia
    private Client requestToEntity(ClientRequest request, Client client) {

        client.setFirst_name(request.getFirst_name());
        client.setLast_name(request.getLast_name());
        client.setPhone(request.getPhone());
        client.setEmail(request.getEmail());
        client.setAppointment(new ArrayList<>());

        return client;
    }

    // Obtener por Id
    private Client find(Long id) {
        
        return this.clientRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Appointment"));

    }

}
