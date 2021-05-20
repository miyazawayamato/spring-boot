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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        
        List<Products> items = productsRepository.findAll();
        
        assertThat(items.get(0).getName(), is("商品A"));
        assertThat(items.get(0).getPrice(), is(200));
        assertThat(items.get(0).getStock(), is(500));
        
        assertThat(items.get(1).getName(), is("商品B"));
        assertThat(items.get(1).getPrice(), is(300));
        assertThat(items.get(1).getStock(), is(750));

    }
    
    @Test
    public void test_create() {
        
        ProductsForm productForm = new ProductsForm();
        productForm.setName("商品A");
        productForm.setPrice(200);
        productForm.setStock(500);
        
        Mockito.when(productsService.create(productForm)).thenReturn(createProduct());
        Products created = productsService.create(productForm);
        assertThat(created.getName(), is("商品A"));
        assertThat(created.getPrice(), is(200));
        assertThat(created.getStock(), is(500));
        
    }
    
    @Test
    public void test_put() {
        
        Products newProduct = new Products();
        newProduct.setId(10);
        newProduct.setName("商品A");
        newProduct.setPrice(200);
        newProduct.setStock(500);
        
        Mockito.when(productsService.put(newProduct)).thenReturn(newProduct);
        
        Products putedProduct = productsService.put(newProduct);
        assertThat(putedProduct.getName(), is("商品A"));
    }
    
    @Test
    public void test_delete() {
        
        Mockito.when(productsRepository.findById(10)).thenReturn(Optional.of(createProduct()));
        
        Optional<Products> product = productsRepository.findById(10);
        Integer num = productsService.delete(product.get().getId());
        
        assertThat(num, is(10));
        
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
