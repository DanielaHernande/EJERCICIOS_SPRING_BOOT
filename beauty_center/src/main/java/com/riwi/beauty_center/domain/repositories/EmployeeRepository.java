package com.riwi.beauty_center.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.beauty_center.domain.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
}
