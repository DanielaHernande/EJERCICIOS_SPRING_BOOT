package com.riwi.beauty_center.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.beauty_center.domain.entities.Servicio;

public interface ServiceRepository extends JpaRepository<Servicio, Long>{
    
}
