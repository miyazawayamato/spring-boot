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


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestApiControllerTest {
    
    @LocalServerPort
	private int port;
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void productsAllGet() throws Exception {
        //実行
        this.mockMvc.perform(get("/api/products"))
        //確認
        .andExpect(status().is(200));
    }
    
    @Test
    void productsCreate() throws Exception {
        
        this.mockMvc.perform(post("/api/product/create"))
        // .contentType("application/json"))
        // .contentType(MediaType.APPLICATION_JSON)
        // .param("name", "testuser")
        // .param("stock", "2000")
        // .param("price", "300"))
        .andExpect(status().isOk());
        
    }
}