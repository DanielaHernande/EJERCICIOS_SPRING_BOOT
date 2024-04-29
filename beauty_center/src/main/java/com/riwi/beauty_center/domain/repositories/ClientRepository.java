package com.riwi.beauty_center.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.beauty_center.domain.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
    
}
