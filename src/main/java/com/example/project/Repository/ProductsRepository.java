package com.example.project.Repository;

import javax.transaction.Transactional;

import com.example.project.Entity.Products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    
    @Transactional
    @Modifying
    @Query(value= "update products p set p.stock = :newStock where p.id = :id" , nativeQuery = true)
    void updateStock(@Param("newStock") Integer newStock, @Param("id") Integer id);
    
}