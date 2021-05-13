package com.example.project.Controller;

import java.util.List;

import com.example.project.Entity.Deliveries;
import com.example.project.Form.DeliveryHistoriesForm;
import com.example.project.Service.DeliveriesService;
import com.example.project.Service.HistoriesService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@RestController
@RequestMapping("/api/derivery")
public class DeliveryHistoryController {
    
    @Autowired
    DeliveriesService deliveriesService;
    
    @Autowired
    HistoriesService historiesService;
    
    
    //納品登録
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Deliveries createDeliveryHistory(@RequestBody DeliveryHistoriesForm form) {
        
        return deliveriesService.create(form);
        
    }
    
    
    //納品履歴 deliとhis entity作ったほうがいい？
    @RequestMapping(value = "/histories", method = RequestMethod.GET)
    public List<Deliveries> getDeliveryHistory() {
        
        List<Deliveries> list = deliveriesService.getAll();
        
        return list;
        
    }
    
    //履歴変更
    // @RequestMapping(value = "/put", method = RequestMethod.PUT)
    // public Deliveries putDeliveryHistory() {
        
    // }
    
    //すべて履歴削除
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Integer deleteDerivery(@PathVariable("id") Integer id) {
        
        
        return deliveriesService.delete(id);
        
        
    }
    
    //一つの商品のみ履歴削除
    @RequestMapping(value = "/delete/products/history/{id}", method = RequestMethod.DELETE)
    public Integer deleteHistory(@PathVariable("id") Integer id) {
        
        return historiesService.delete(id);
        
    }
}
