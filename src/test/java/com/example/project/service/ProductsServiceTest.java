package com.example.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.project.Entity.Products;
import com.example.project.Form.ProductsForm;
import com.example.project.Repository.ProductsRepository;
import com.example.project.Service.ProductsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javassist.expr.NewArray;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ProductsServiceTest {
    
    @Mock
    private ProductsRepository productsRepository;

    @InjectMocks
    private ProductsService productsService;

    @BeforeEach
    public void initmocks() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void test_allget() {
        
        Mockito.when(productsRepository.findAll()).thenReturn(createProductsId10());
        
        List<Products> items = productsService.getAll();
        
        assertThat(items.get(0).getName(), is("商品A"));
        assertThat(items.get(0).getPrice(), is(200));
        assertThat(items.get(0).getStock(), is(500));
        
        assertThat(items.get(1).getName(), is("商品B"));
        assertThat(items.get(1).getPrice(), is(300));
        assertThat(items.get(1).getStock(), is(750));
        
        verify(productsRepository, times(1)).findAll();
    }
    
    @Test
    public void test_create() {
        
        Products product = new Products();
        ProductsForm productForm = new ProductsForm();
        
        Mockito.when(productsRepository.save(any(Products.class))).thenReturn(product);
        productsService.create(productForm);
        
        verify(productsRepository, times(1)).save(product);
    }
    
    @Test
    public void test_put() {
        
        Mockito.when(productsRepository.findById(createProduct().getId())).thenReturn(Optional.of(createProduct()));
        Mockito.when(productsRepository.save(createProduct())).thenReturn(createProduct());
        
        Products putedProduct = productsService.put(createProduct());
        
        assertThat(putedProduct.getName(), is("商品A"));
        
        verify(productsRepository, times(1)).save(createProduct());
        verify(productsRepository, times(1)).findById(createProduct().getId());
        
    }
    
    @Test
    public void test_delete() {
        
        Mockito.when(productsRepository.findById(10)).thenReturn(Optional.of(createProduct()));
        
        Integer num = productsService.delete(10);
        
        assertThat(num, is(10));
        
        verify(productsRepository, times(1)).findById(10);
        verify(productsRepository, times(1)).deleteById(10);
        
    }
    
    @Test
    public void test_calcStock() {
        
        List<ProductsForm> lists = new ArrayList<>();
        ProductsForm item1 = new ProductsForm();
        item1.setId(1);
        item1.setStock(12);
        lists.add(item1);
        lists.add(item1);
        lists.add(item1);
        
        Mockito.doNothing().when(productsRepository).updateStock(anyInt(), anyInt());
        productsService.calcStock(lists);
        
        verify(productsRepository, times(3)).updateStock(anyInt(), anyInt());
        
    }
    
    public Products createProduct() {
        
        Products product = new Products();
        product.setId(10);
        product.setName("商品A");
        product.setPrice(200);
        product.setStock(500);
        return product;
    }
    
    public List<Products> createProductsId10() {
        
        List<Products> items = new ArrayList<>();
        
        Products product1 = new Products();
        product1.setId(10);
        product1.setName("商品A");
        product1.setPrice(200);
        product1.setStock(500);
        Products product2 = new Products();
        product2.setId(15);
        product2.setName("商品B");
        product2.setPrice(300);
        product2.setStock(750);
        
        items.add(product1);
        items.add(product2);
        
        return items;
    }
}
