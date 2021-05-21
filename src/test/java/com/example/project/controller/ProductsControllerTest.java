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
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.project.Entity.Products;
import com.example.project.Form.ProductsForm;
import com.example.project.Service.ProductsService;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductsControllerTest {
    
    @LocalServerPort
	private int port;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Mock
    private ProductsService productsService;

    @BeforeEach
    public void initmocks() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void productsGet() throws Exception {
        //実行
        this.mockMvc.perform(get("/api/product/all"))
        //確認
        .andExpect(status().is(200));
    }
    
    @Test
    void createProducts() throws Exception {
        
        this.mockMvc.perform(post("/api/product/create")
        .contentType("application/json")
        .content("{\"name\":\"yamato\",\"price\":\"200\",\"stock\":\"99\"}"))
        .andExpect(status().is(200))
        .andExpect(jsonPath("$.name").value("yamato"))
        .andExpect(jsonPath("$.price").value("200"))
        .andExpect(jsonPath("$.stock").value("99"));
        
    }
    @Test
    void putProducts() throws Exception {
        
        Mockito.when(productsService.create(createProductForm())).thenReturn(createProduct());
        Products product = productsService.create(createProductForm());
        
        this.mockMvc.perform(put("/api/product/put")
        .contentType("application/json")
        .content("{\"id\":\""+product.getId()+"\",\"name\":\"change\",\"price\":\"200\",\"stock\":\"99\"}"))
        .andExpect(status().is(200));
        
    }
    
    @Test
    void deleteProducts() throws Exception {
        
        Mockito.when(productsService.create(createProductForm())).thenReturn(createProduct());
        Products created = productsService.create(createProductForm());
        
        this.mockMvc.perform(delete("/api/product/delete/" + created.getId()))
        .andExpect(status().is(200));
        
    }
    
    public Products createProduct() {
        
        Products product = new Products();
        product.setId(10);
        product.setName("商品A");
        product.setPrice(200);
        product.setStock(500);
        return product;
    }
    public ProductsForm createProductForm() {
        
        ProductsForm productForm = new ProductsForm();
        productForm.setName("商品A");
        productForm.setPrice(200);
        productForm.setStock(500);
        return productForm;
    }
}