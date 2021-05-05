package com.example.project.Controller;

import java.util.List;

import com.example.project.Entity.Deliveries;
import com.example.project.Form.DeliveryHistoriesForm;
import com.example.project.Service.DeliveriesService;
import com.example.project.Service.HistoriesService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    //定義済みを利用
    //納品画面 企業選択
    // @RequestMapping(value = "/menu", method = RequestMethod.GET)
    //納品画面 商品選択
    // @RequestMapping(value = "/menu", method = RequestMethod.GET)
    
    
    
    //納品登録
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public DeliveryHistoriesForm createDeliveryHistory(@RequestBody DeliveryHistoriesForm form) {
        
        return form;
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
    
    //履歴削除
    // @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
}
