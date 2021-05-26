package com.example.project.controller;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

import com.example.project.Controller.ProductsController;
import com.example.project.Entity.Products;
import com.example.project.Form.ProductsForm;
import com.example.project.Service.ProductsService;

@WebMvcTest(ProductsController.class)
public class ProductsControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ProductsService productsService;
    
    @Test
    void productsGet() throws Exception {
        
        List<Products> lists =  new ArrayList<>();
        Products item = createProduct();
        lists.add(item);
        
        Mockito.when(productsService.getAll()).thenReturn(lists);
        
        this.mockMvc.perform(get("/api/product/all"))
        .andExpect(status().is(200))
        .andExpect(jsonPath("$.[0].id").value("10"))
        .andExpect(jsonPath("$.[0].name").value("商品A"))
        .andExpect(jsonPath("$.[0].price").value("200"))
        .andExpect(jsonPath("$.[0].stock").value("500"));
        
        verify(productsService, times(1)).getAll();
        
    }
    
    @Test
    void createProducts() throws Exception {
        
        Mockito.when(productsService.create(createProductForm())).thenReturn(createProduct());
        
        this.mockMvc.perform(post("/api/product/create")
        .contentType("application/json")
        .content("{\"name\":\"商品A\",\"price\":\"200\",\"stock\":\"500\"}"))
        .andExpect(status().is(200))
        .andExpect(jsonPath("$.id").value("10"))
        .andExpect(jsonPath("$.name").value("商品A"))
        .andExpect(jsonPath("$.price").value("200"))
        .andExpect(jsonPath("$.stock").value("500"));
        
        verify(productsService, times(1)).create(createProductForm());
        
    }
    @Test
    void putProducts() throws Exception {
        
        Mockito.when(productsService.put(createProduct())).thenReturn(createProduct());
        
        this.mockMvc.perform(put("/api/product/put")
        .contentType("application/json")
        .content("{\"id\":\"10\",\"name\":\"商品A\",\"price\":\"200\",\"stock\":\"500\"}"))
        .andExpect(status().is(200))
        .andExpect(jsonPath("$.id").value("10"))
        .andExpect(jsonPath("$.name").value("商品A"))
        .andExpect(jsonPath("$.price").value("200"))
        .andExpect(jsonPath("$.stock").value("500"));
        
        verify(productsService, times(1)).put(createProduct());
    }
    
    @Test
    void deleteProducts() throws Exception {
        
        Mockito.when(productsService.delete(10)).thenReturn(10);
        
        this.mockMvc.perform(delete("/api/product/delete/10"))
        .andExpect(status().is(200)).andExpect(content().string("10"));
        
        verify(productsService, times(1)).delete(10);
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