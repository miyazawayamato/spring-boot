package com.example.project.entity;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.example.project.Entity.Products;
import com.example.project.Repository.ProductsRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;



import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

// @RunWith(SpringRunner::class)
@DataJpaTest
@AutoConfigureDataJpa
public class ProductsEntityTest {
    
    @Autowired
    TestEntityManager testEntityManager;
    
    @Autowired
    ProductsRepository productsRepository;
    
    @Test
    public void proentity() {
        
        testEntityManager.persist(createProduct());
        testEntityManager.persist(createProduct2());
        
        List<Products> items = productsRepository.findAll();
        assertThat(items.get(0).getName(), is("商品A"));
    }
    
    public Products createProduct() {
        
        Products product = new Products();
        product.setId(10);
        product.setName("商品A");
        product.setPrice(200);
        product.setStock(500);
        return product;
    }
    public Products createProduct2() {
        
        Products product = new Products();
        product.setId(11);
        product.setName("商品B");
        product.setPrice(100);
        product.setStock(300);
        return product;
    }
}
