package com.example.project.Service;

// import java.util.ArrayList;
import java.util.List;

import com.example.project.Entity.Deliveries;
// import com.example.project.Entity.Histories;
import com.example.project.Form.DeliveryHistoriesForm;
import com.example.project.Form.HistoriesForm;
import com.example.project.Form.ProductsForm;
// import com.example.project.Form.ProductsForm;
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
    
    @Autowired
	ProductsService productsService;
    
    public List<Deliveries> getAll() {
        
        return deliveriesRepository.findAll();
    }
    
    public Deliveries create(DeliveryHistoriesForm form) {
        
        // IDをhistoriesに登録するため、deliveriesに最初に保存
        Deliveries newData = new Deliveries();
        newData.setCustomer(form.getCustomer());
        Deliveries deli = deliveriesRepository.save(newData);
        
        //formからlistの部分(products)を取得
        List<HistoriesForm> his = form.getHistories();
        //deliIDをセット
        for(HistoriesForm s : his){
            s.setDeliveries_id(deli.getId());
        }
        //一括挿入
        historiesRepository.saveAll(his);
        
        //現在在庫-納品個数
        List<ProductsForm> deliStock = form.getProducts();
        productsService.calcStock(deliStock);
        
        return newData;
    }
}
