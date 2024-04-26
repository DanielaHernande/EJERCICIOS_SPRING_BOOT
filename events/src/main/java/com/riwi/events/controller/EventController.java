package com.riwi.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.events.entities.Event;
import com.riwi.events.service.abstract_service.IEventService;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/event")
public class EventController {
    
    @Autowired
    private final IEventService objIEventService;

    // Metodo para listar
    @GetMapping
    public  ResponseEntity<Page<Event>> list(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "6") int size) {

            Page<Event> listEvent = this.objIEventService.getAllPagination(page, size);
            return ResponseEntity.ok(listEvent);
    }

    @GetMapping(path = "/listar")
    public ResponseEntity<List<Event>> listar() {
            return  ResponseEntity.ok(this.objIEventService.getAll());
    }

    // Buscar Id
    @GetMapping(path = "/{id}")
    public ResponseEntity<Event> get(@PathVariable String id) {

        return ResponseEntity.ok(this.objIEventService.getById(id));
    }

    // Metodo para ingresar un nuevo dato
    @PostMapping(path = "/insert")
    public ResponseEntity<Event> insert(@RequestBody Event objEvent) {

        return ResponseEntity.ok(this.objIEventService.save(objEvent));
    }

    // Metodo para actualizar
    @PutMapping(path = "/{id}")
    public ResponseEntity<Event> update(
        @RequestBody Event objEvent, 
        @PathVariable String id) {

            objEvent.setId(id);
            return ResponseEntity.ok(this.objIEventService.update(objEvent));
    }

    // Metodo para elimianr
    @DeleteMapping(path = "/{id}") 
    public ResponseEntity<Void> delete(@PathVariable String id) {

        this.objIEventService.delete(id);

        return ResponseEntity.noContent().build();
    }   
}
