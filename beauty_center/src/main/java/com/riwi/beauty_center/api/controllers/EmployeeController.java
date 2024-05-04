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

import com.riwi.beauty_center.api.dto.request.EmployeeRequest;
import com.riwi.beauty_center.api.dto.response.EmployeeResponse;
import com.riwi.beauty_center.infrastructure.abastract_services.IEmployeeService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/employee")
public class EmployeeController {

    @Autowired
    private final IEmployeeService employeeService;

    // Listar los empleados
    @GetMapping
    public ResponseEntity<Page<EmployeeResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size) {
        
        return ResponseEntity.ok(this.employeeService.getAll(page, size));
    }

    // Obtener por Id
    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeResponse> getById(
            @PathVariable Long id) {
    
        return ResponseEntity.ok(this.employeeService.getById(id));
    }

    // Crear
    @PostMapping
    public ResponseEntity<EmployeeResponse> insert(
            @Validated @RequestBody EmployeeRequest client) {

        return ResponseEntity.ok(this.employeeService.create(client));
    }

    // Actualizar
    @PutMapping(path = "/{id}")
    public ResponseEntity<EmployeeResponse> update(
            @PathVariable Long id,
            @Validated @RequestBody EmployeeRequest client) {

        return ResponseEntity.ok(this.employeeService.update(client, id));
    }

    // Eliminar
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        
        this.employeeService.delete(id);

        return ResponseEntity.noContent().build();
    }



    
}
