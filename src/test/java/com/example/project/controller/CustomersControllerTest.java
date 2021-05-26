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

import com.example.project.Controller.CustomersController;
import com.example.project.Entity.Customers;
import com.example.project.Form.CustomersForm;
import com.example.project.Service.CustomersService;


@WebMvcTest(CustomersController.class)
public class CustomersControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private CustomersService customersService;
    
    @BeforeEach
    public void initmocks() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void getCustomersTest() throws Exception {
        
        List<Customers> lists =  new ArrayList<>();
        Customers item = createCustomer();
        lists.add(item);
        
        Mockito.when(customersService.getAll()).thenReturn(lists);
        
        this.mockMvc.perform(get("/api/customers"))
        .andExpect(status().is(200))
        .andExpect(jsonPath("$.[0].id").value("10"))
        .andExpect(jsonPath("$.[0].name").value("企業A"))
        .andExpect(jsonPath("$.[0].address").value("B県C市D番地"))
        .andExpect(jsonPath("$.[0].phone").value("1234567890"));
        
        verify(customersService, times(1)).getAll();
        
    }
    
    @Test
    void createCustomerTest() throws Exception {
        
        Mockito.when(customersService.create(createCustomerForm())).thenReturn(createCustomer());
        
        this.mockMvc.perform(post("/api/customer/create")
        .contentType("application/json")
        .content("{\"name\":\"企業A\",\"phone\":\"1234567890\",\"address\":\"B県C市D番地\"}"))
        .andExpect(status().is(200))
        .andExpect(jsonPath("$.id").value("10"))
        .andExpect(jsonPath("$.name").value("企業A"))
        .andExpect(jsonPath("$.phone").value("1234567890"))
        .andExpect(jsonPath("$.address").value("B県C市D番地"));
        
        verify(customersService, times(1)).create(createCustomerForm());
        
    }
    
    @Test
    void putCustomerTest() throws Exception {
        
        Mockito.when(customersService.put(createCustomer())).thenReturn(createCustomer());
        
        this.mockMvc.perform(put("/api/customer/put")
        .contentType("application/json")
        .content("{\"id\":\"10\",\"name\":\"企業A\",\"phone\":\"1234567890\",\"address\":\"B県C市D番地\"}"))
        .andExpect(status().is(200))
        .andExpect(jsonPath("$.id").value("10"))
        .andExpect(jsonPath("$.name").value("企業A"))
        .andExpect(jsonPath("$.phone").value("1234567890"))
        .andExpect(jsonPath("$.address").value("B県C市D番地"));
        
        verify(customersService, times(1)).put(createCustomer());
        
    }
    @Test
    void deleteProducts() throws Exception {
        
        Mockito.when(customersService.delete(10)).thenReturn(10);
        
        this.mockMvc.perform(delete("/api/customer/delete/10"))
        .andExpect(status().is(200))
        .andExpect(content().string("10"));
        
        verify(customersService, times(1)).delete(10);
        
    }
    
    public Customers createCustomer() {
        
        Customers customer = new Customers();
        customer.setId(10);
        customer.setName("企業A");
        customer.setAddress("B県C市D番地");
        customer.setPhone("1234567890");
        return customer;
    }
    public CustomersForm createCustomerForm() {
        
        CustomersForm CustomerForm = new CustomersForm();
        CustomerForm.setName("企業A");
        CustomerForm.setAddress("B県C市D番地");
        CustomerForm.setPhone("1234567890");
        return CustomerForm;
    }
}
