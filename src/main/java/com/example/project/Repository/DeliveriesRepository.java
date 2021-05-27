package com.example.project.Repository;


import com.example.project.Entity.Deliveries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveriesRepository extends JpaRepository<Deliveries, Integer> {

    
}
