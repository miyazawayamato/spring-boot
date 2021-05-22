package com.example.project.entity;

import com.example.project.Repository.ProductsRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

// @RunWith(SpringRunner::class)
@DataJpaTest
public class ProductsEntityTest {
    
    @Autowired
    TestEntityManager TestEntityManager;
    
    @Autowired
    ProductsRepository ProductsRepository;
    
    @Test
    public void proentity() {
        // TestEntityManager.persist(Products(name="yamato", "id" = "ij"));
    }
}
