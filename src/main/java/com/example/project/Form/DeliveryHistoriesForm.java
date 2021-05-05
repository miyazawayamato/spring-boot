package com.example.project.Form;

import java.util.List;

import lombok.Data;


@Data
public class DeliveryHistoriesForm  {
    
    //企業id
    
    private Integer cutomerId;
    private List<ProductsForm> products;
    
    //下記はlist
    // 商品id
    // 個数
    // 金額
}