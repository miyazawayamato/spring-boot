package com.example.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.project.Entity.Deliveries;
import com.example.project.Repository.DeliveriesRepository;
import com.example.project.Repository.HistoriesRepository;
import com.example.project.Service.DeliveriesService;
import com.example.project.Service.ProductsService;
import com.example.project.Form.DeliveryHistoriesForm;
import com.example.project.Form.HistoriesForm;
import com.example.project.Form.ProductsForm;

public class DeliveriesServicTest {
    
    @Mock
    private DeliveriesRepository deliveriesRepository;
    @Mock
    private HistoriesRepository historiesRepository;
    @Mock
    private ProductsService productsService;

    @InjectMocks
    private DeliveriesService deliveriesService;

    @BeforeEach
    public void initmocks() {
        
        MockitoAnnotations.openMocks(this);
        
    }
    
    @Test
    public void test_create() throws Exception {
        
        Deliveries delivery = new Deliveries();
        DeliveryHistoriesForm form = new DeliveryHistoriesForm();
        List<HistoriesForm> hList = new ArrayList<>();
        List<ProductsForm> pList = new ArrayList<>();
        form.setHistories(hList);
        form.setProducts(pList);
        
        Mockito.when(deliveriesRepository.save(any(Deliveries.class))).thenReturn(delivery);
        Mockito.doNothing().when(productsService).calcStock(pList);
        
        deliveriesService.create(form);
        
        verify(deliveriesRepository, times(1)).save(delivery);
        verify(historiesRepository, times(1)).saveAll(hList);
        verify(productsService, times(1)).calcStock(pList);
        
    }
    
    
    @Test
    public void test_delete() throws Exception {
        
        Deliveries delivery = new Deliveries();
        Mockito.when(deliveriesRepository.findById(10)).thenReturn(Optional.of(delivery));
        Integer num = deliveriesService.delete(10);
        
        assertThat(num, is(10));
        
        verify(deliveriesRepository, times(1)).findById(10);
        verify(deliveriesRepository, times(1)).deleteById(10);
        verify(historiesRepository, times(1)).deleteHistories(10);
    }
    
}
