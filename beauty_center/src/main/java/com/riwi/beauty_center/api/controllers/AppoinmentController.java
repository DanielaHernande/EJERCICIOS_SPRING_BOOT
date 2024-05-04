package com.riwi.beauty_center.api.controllers;

import java.util.HashMap;
import java.util.Map;

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

import com.riwi.beauty_center.api.dto.request.AppointmentRequest;
import com.riwi.beauty_center.api.dto.response.AppointmentResponse;
import com.riwi.beauty_center.infrastructure.abastract_services.IAppointmentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/appointment")
public class AppoinmentController {
    
    @Autowired
    public final IAppointmentService appointmentService;

    // Listar las citas
    @GetMapping
    public ResponseEntity<Page<AppointmentResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size) {
        
        return ResponseEntity.ok(this.appointmentService.getAll(page - 1, size));
    }

    // Obtener por Id
    @GetMapping(path = "/{id}")
    public ResponseEntity<AppointmentResponse> getById(
            @PathVariable Long id) {
    
        return ResponseEntity.ok(this.appointmentService.getById(id));
    }

    // Crear
    @PostMapping
    public ResponseEntity<AppointmentResponse> insert(
            @Validated @RequestBody AppointmentRequest client) {

        return ResponseEntity.ok(this.appointmentService.create(client));
    }

    // Actualizar
    @PutMapping(path = "/{id}")
    public ResponseEntity<AppointmentResponse> update(
            @PathVariable Long id,
            @Validated @RequestBody AppointmentRequest client) {

        return ResponseEntity.ok(this.appointmentService.update(client, id));
    }

    // Eliminar
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> delete(
            @PathVariable Long id) {

        Map<String, String> response = new HashMap<>();

        response.put("message", "Se elimino la cita correctamente");

        this.appointmentService.delete(id);
        return ResponseEntity.ok(response);
    }
}
