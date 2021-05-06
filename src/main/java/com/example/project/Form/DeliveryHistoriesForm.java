package com.example.project.Form;

import java.util.List;

import com.example.project.Entity.Histories;

import lombok.Data;


@Data
public class DeliveryHistoriesForm  {
    
    //企業id
    
    private Integer cutomerId;
    private List<HistoriesForm> histories;
    
    //下記はlist
    // 商品id
    // 個数
    // 金額
}