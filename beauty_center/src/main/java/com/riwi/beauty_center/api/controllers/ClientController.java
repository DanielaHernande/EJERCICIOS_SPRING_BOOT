package com.riwi.beauty_center.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.beauty_center.api.dto.request.ClientRequest;
import com.riwi.beauty_center.api.dto.response.ClientResponse;
import com.riwi.beauty_center.infrastructure.abastract_services.IClientService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/client")
public class ClientController {
    
    @Autowired
    private final IClientService clientService;

    // Listar los clientes
    @GetMapping
    public ResponseEntity<Page<ClientResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size) {
        
        return ResponseEntity.ok(this.clientService.getAll(page - 1, size));
    }

    // Obtener por Id
    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientResponse> getById(
            @PathVariable Long id) {
    
        return ResponseEntity.ok(this.clientService.getById(id));
    }

    // Crear
    @PostMapping
    public ResponseEntity<ClientResponse> insert(
            @Validated @RequestBody ClientRequest client) {

        return ResponseEntity.ok(this.clientService.create(client));
    }

    // Actualizar
    @PutMapping(path = "/{id}")
    public ResponseEntity<ClientResponse> update(
            @PathVariable Long id,
            @Validated @RequestBody ClientRequest client) {

        return ResponseEntity.ok(this.clientService.update(client, id));
    }

    // Eliminar
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        
        this.clientService.delete(id);

        return ResponseEntity.noContent().build();
    }


}
