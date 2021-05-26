package com.example.project.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import com.example.project.Controller.DeliveryHistoryController;
import com.example.project.Entity.Deliveries;
import com.example.project.Entity.Histories;
import com.example.project.Form.DeliveryHistoriesForm;
import com.example.project.Service.DeliveriesService;
import com.example.project.Service.HistoriesService;

@WebMvcTest(DeliveryHistoryController.class)
public class DeliveryHistoryControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private DeliveriesService deliveriesService;
    @MockBean
    private HistoriesService historiesService;
    
    @BeforeEach
    public void initmocks() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void test_createDeliveryHistory() throws Exception {
        
        DeliveryHistoriesForm form = new DeliveryHistoriesForm();
        Mockito.when(deliveriesService.create(form)).thenReturn(createDeliveries());
        
        this.mockMvc.perform(post("/api//derivery/register")
        .contentType("application/json")
        .content("{\"customer\":\"企業A\",\"histories\":[{\"name\":\"商品A\",\"price\":\"200\",\"qty\":\"30\"},{\"name\":\"商品B\",\"price\":\"400\",\"qty\":\"60\"}],\"products\":[{\"id\":\"10\",\"stock\":\"30\"}]}"))
        .andExpect(status().is(200));
        
        
    }
    @Test
    public void test_getDeliveryHistory() throws Exception {
        
        List<Deliveries> lists =  new ArrayList<>();
        lists.add(createDeliveries());
        
        Mockito.when(deliveriesService.getAll()).thenReturn(lists);
        
        this.mockMvc.perform(get("/api/derivery/histories"))
        .andExpect(status().is(200))
        .andExpect(jsonPath("$.[0].id").value("1"))
        .andExpect(jsonPath("$.[0].customer").value("企業A"))
        .andExpect(jsonPath("$.[0].histories.[0].id").value("10"))
        .andExpect(jsonPath("$.[0].histories.[0].name").value("商品A"))
        .andExpect(jsonPath("$.[0].histories.[0].price").value("200"))
        .andExpect(jsonPath("$.[0].histories.[0].qty").value("30"))
        .andExpect(jsonPath("$.[0].histories.[1].id").value("20"))
        .andExpect(jsonPath("$.[0].histories.[1].name").value("商品B"))
        .andExpect(jsonPath("$.[0].histories.[1].price").value("400"))
        .andExpect(jsonPath("$.[0].histories.[1].qty").value("60"));
        
        verify(deliveriesService, times(1)).getAll();
        
    }
    
    @Test
    public void test_deleteDerivery() throws Exception {
        
        Mockito.when(deliveriesService.delete(10)).thenReturn(10);
        
        this.mockMvc.perform(delete("/api/derivery/delete/10"))
        .andExpect(status().is(200))
        .andExpect(content().string("10"));
        
        verify(deliveriesService, times(1)).delete(10);
        
    }
    @Test
    public void test_deleteHistory() throws Exception {
        
        Mockito.when(historiesService.delete(10)).thenReturn(10);
        
        this.mockMvc.perform(delete("/api/derivery/delete/products/history/10"))
        .andExpect(status().is(200))
        .andExpect(content().string("10"));
        
        verify(historiesService, times(1)).delete(10);
        
    }
    
    public Deliveries createDeliveries() {
        
        Deliveries deli = new Deliveries();
        deli.setId(1);
        deli.setCustomer("企業A");
        deli.setHistories(createHistoriesr());
        
        return deli;
    }
    public List<Histories> createHistoriesr() {
        
        List<Histories> histories = new ArrayList<>();
        Histories item1 = new Histories();
        item1.setId(10);
        item1.setName("商品A");
        item1.setPrice(200);
        item1.setQty(30);
        Histories item2 = new Histories();
        item2.setId(20);
        item2.setName("商品B");
        item2.setPrice(400);
        item2.setQty(60);
        histories.add(item1);
        histories.add(item2);
        
        return histories;
    }
}
