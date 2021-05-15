package com.example.project.Intergration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductsControllerTest {
    
    @LocalServerPort
	private int port;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void productsGet() throws Exception {
        //実行
        this.mockMvc.perform(get("/api/product/all"))
        //確認
        .andExpect(status().is(200));
    }
    
    @Test
    void createProducts() throws Exception {

        
        // this.mockMvc.perform(post("/api/product/create")
        // .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        // .content("name" = "gysg"))
        // .andExpect(status().is(200));
        
        
        // mockMvc.perform( MockMvcRequestBuilders
        // .post("/api/product/create")
        // .content(asJsonString(new ProductsForm()))
        // .contentType(MediaType.APPLICATION_JSON)
        // .accept(MediaType.APPLICATION_JSON))
        // .andExpect(status().isCreated());
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}