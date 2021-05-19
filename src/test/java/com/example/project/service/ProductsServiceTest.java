package com.example.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.project.Entity.Products;
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

public class ProductsServiceTest {
    
    @Mock   // モックオブジェクトとして使用することを宣言
    private ProductsRepository productsRepository;

    @InjectMocks    // モックオブジェクトの注入
    private ProductsService productsService;

    @BeforeEach // テストメソッド（@Testをつけたメソッド）実行前に都度実施
    public void initmocks() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void test_findByCategoryId() {
        
        Mockito.when(productsRepository.findAll()).thenReturn(getProductsId10());
        
        List<Products> items = productsRepository.findAll();

        assertThat(items.get(0).getName(), is("商品A"));
        assertThat(items.get(0).getPrice(), is(200));
        assertThat(items.get(0).getStock(), is(500));
        
        assertThat(items.get(1).getName(), is("商品B"));
        assertThat(items.get(1).getPrice(), is(300));
        assertThat(items.get(1).getStock(), is(750));

    }
    
    
    public List<Products> getProductsId10() {
        
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
