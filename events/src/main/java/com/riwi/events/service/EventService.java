package com.riwi.events.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.riwi.events.entities.Event;
import com.riwi.events.repositories.EventRepository;
import com.riwi.events.service.abstract_service.IEventService;
import com.riwi.events.service.validaciones.Validacion;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventService implements IEventService{

    @Autowired
    private final EventRepository objEventRepository;
    
    // Crear
    @Override
    public Event save(Event objEvent) {
        
        if (objEvent.getCapacity() < 0) {

            // throw new es para lanzar una excepcion
            // Validacion es un constructor 
            throw new Validacion("No se puede poner numeros negativos");
        }
        return this.objEventRepository.save(objEvent);
    }

    @Override
    public List<Event> getAll() {
        return this.objEventRepository.findAll();
    }

    @Override
    public Page<Event> getAllPagination(int page, int size) {

        if (page < 0) {
            page = 0;
        }

        Pageable objPage = PageRequest.of(page, size);
        return this.objEventRepository.findAll(objPage);
    }

    // Obtener por Id
    @Override
    public Event getById(String id) {
        return this.objEventRepository.findById(id).orElseThrow();
    }

    // Actualizar
    @Override
    public Event update(Event objEvent) {
        return this.objEventRepository.save(objEvent);
    }

    // Eliminar
    @Override
    public void delete(String id) {
        Event event = this.objEventRepository.findById(id).orElseThrow();

        this.objEventRepository.delete(event);
    }

}
