package com.example.project.Service;

import java.util.ArrayList;
import java.util.List;
// import java.util.Optional;

import com.example.project.Entity.Deliveries;
import com.example.project.Entity.Histories;
import com.example.project.Form.DeliveryHistoriesForm;
import com.example.project.Form.HistoriesForm;
import com.example.project.Form.ProductsForm;
import com.example.project.Repository.DeliveriesRepository;
import com.example.project.Repository.HistoriesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeliveriesService {
    
    @Autowired
    DeliveriesRepository deliveriesRepository;
    
    @Autowired
    HistoriesRepository historiesRepository;
    
    public List<Deliveries> getAll() {
        
        return deliveriesRepository.findAll();
    }
    
    public Deliveries create(DeliveryHistoriesForm form) {
        
        Deliveries newData = new Deliveries();
        
        newData.setCustomers_id(form.getCutomerId());
        // newData.setHistories(form.getHistories());
        Deliveries deli = deliveriesRepository.save(newData);
        List<HistoriesForm> his = form.getHistories();
        
        // List<Histories> his = new ArrayList<Histories>(form.getHistories());
        // List<データ型名> オブジェクト名 = new ArrayList<データ型名>();
        for(HistoriesForm s : his){
            
            s.setDeliveries_id(deli.getId());
            historiesRepository.save(s);
        }
        // return historiesRepository.saveAll(form.getHistories());
        return newData;
        
        // return newData;
    }
}
