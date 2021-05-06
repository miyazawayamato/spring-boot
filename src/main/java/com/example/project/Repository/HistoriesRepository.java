package com.example.project.Repository;


// import com.example.project.Entity.Histories;
import com.example.project.Form.HistoriesForm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriesRepository  extends JpaRepository<HistoriesForm, Integer> {
    
}
