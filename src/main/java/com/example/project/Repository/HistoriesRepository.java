package com.example.project.Repository;


import com.example.project.Entity.Histories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriesRepository  extends JpaRepository<Histories, Integer> {
    
}
