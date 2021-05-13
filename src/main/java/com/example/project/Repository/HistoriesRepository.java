package com.example.project.Repository;


import javax.transaction.Transactional;

// import com.example.project.Entity.Histories;
import com.example.project.Form.HistoriesForm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface HistoriesRepository  extends JpaRepository<HistoriesForm, Integer> {
    
    @Transactional
    @Modifying
    @Query(value= "delete from histories where deliveries_id = :hId" , nativeQuery = true)
    void deleteHistories(@Param("hId") Integer hId);
}
