package com.example.project.controller;


import org.junit.jupiter.api.Test;
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


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomersControllerTest {
    
    
    @LocalServerPort
	private int port;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void getCustomers() throws Exception {
        //実行
        this.mockMvc.perform(get("/api/customers"))
        //確認
        .andExpect(status().is(200));
    }
    @Test
    void customersGet() throws Exception {
        //実行
        this.mockMvc.perform(post("/api/customer/create"))
        //確認
        .andExpect(status().is(200));
    }
    
    
}
