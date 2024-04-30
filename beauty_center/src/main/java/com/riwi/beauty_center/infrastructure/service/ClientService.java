package com.riwi.beauty_center.infrastructure.service;

import com.riwi.beauty_center.api.dto.request.ClientRequest;
import com.riwi.beauty_center.api.dto.response.ClientResponse;
import com.riwi.beauty_center.domain.entities.Client;
import com.riwi.beauty_center.domain.repositories.ClientRepository;
import com.riwi.beauty_center.infrastructure.abastract_services.IClientService;
import lombok.AllArgsConstructor;
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
    @Override
    public Page<ClientResponse> getAll(int page, int size) {

        if (page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);
        return this.clientRepository.findAll(pagination).map(client -> this.entityToResponse())
        return null;
    }

    @Override
    public ClientResponse create(ClientRequest request) {
        return null;
    }

    @Override
    public ClientResponse update(ClientRequest request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public ClientResponse getById(Long aLong) {
        return null;
    }

    private ClientResponse entityToResponse(Client entity) {
        ClientResponse response = new ClientResponse();

        BeanUtils.copyProperties(entity, response);

        response.setAppointment(entity.getAppointment().stream().map(this::));

        return response;
    }

    private App
}
