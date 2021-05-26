package com.example.project.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
// import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

// import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.project.Entity.Customers;
import com.example.project.Form.CustomersForm;
import com.example.project.Service.CustomersService;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomersControllerTest {
    
    @LocalServerPort
	private int port;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Mock
    private CustomersService customersService;

    @BeforeEach
    public void initmocks() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void getCustomersTest() throws Exception {
        //実行
        this.mockMvc.perform(get("/api/customers"))
        //確認
        .andExpect(status().is(200));
    }
    
    @Test
    void createCustomerTest() throws Exception {
        
        this.mockMvc.perform(post("/api/customer/create")
        .contentType("application/json")
        .content("{\"name\":\"company\",\"phone\":\"1234567890\",\"address\":\"citycity\"}"))
        .andExpect(status().is(200))
        .andExpect(jsonPath("$.name").value("company"))
        .andExpect(jsonPath("$.phone").value("1234567890"))
        .andExpect(jsonPath("$.address").value("citycity"));
        
    }
    
    @Test
    void putCustomerTest() throws Exception {
        
        Mockito.when(customersService.create(createCustomerForm())).thenReturn(createCustomer());
        Customers customer = customersService.create(createCustomerForm());
        
        this.mockMvc.perform(put("/api/customer/put")
        .contentType("application/json")
        .content("{\"id\":\""+customer.getId()+"\",\"name\":\"change\",\"phone\":\"200\",\"address\":\"99\"}"))
        .andExpect(status().is(200));
        
    }
    @Test
    void deleteProducts() throws Exception {
        
        Mockito.when(customersService.create(createCustomerForm())).thenReturn(createCustomer());
        Customers created = customersService.create(createCustomerForm());
        
        this.mockMvc.perform(delete("/api/customer/delete/" + created.getId()))
        .andExpect(status().is(200)).andExpect(content().string("0"));
        
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
