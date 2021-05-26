package com.example.project.Repository;

import java.util.List;

import com.example.project.Entity.Deliveries;
import com.example.project.Form.HistoriesForm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveriesRepository extends JpaRepository<Deliveries, Integer> {

    
}
