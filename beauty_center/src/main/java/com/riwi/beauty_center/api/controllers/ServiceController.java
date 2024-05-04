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

import com.riwi.beauty_center.api.dto.request.ServiceRequest;
import com.riwi.beauty_center.api.dto.response.ServiceResponse;
import com.riwi.beauty_center.infrastructure.abastract_services.IServiceService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/service")
public class ServiceController {
    
    @Autowired
    private final IServiceService serviceService;

        // Listar los empleados
    @GetMapping
    public ResponseEntity<Page<ServiceResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size) {
        
        return ResponseEntity.ok(this.serviceService.getAll(page, size));
    }

    // Obtener por Id
    @GetMapping(path = "/{id}")
    public ResponseEntity<ServiceResponse> getById(
            @PathVariable Long id) {
    
        return ResponseEntity.ok(this.serviceService.getById(id));
    }

    // Crear
    @PostMapping
    public ResponseEntity<ServiceResponse> insert(
            @Validated @RequestBody ServiceRequest client) {

        return ResponseEntity.ok(this.serviceService.create(client));
    }

    // Actualizar
    @PutMapping(path = "/{id}")
    public ResponseEntity<ServiceResponse> update(
            @PathVariable Long id,
            @Validated @RequestBody ServiceRequest client) {

        return ResponseEntity.ok(this.serviceService.update(client, id));
    }

    // Eliminar
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        
        this.serviceService.delete(id);

        return ResponseEntity.noContent().build();
    }


}
