package com.example.project.Service;


import com.example.project.Entity.Products;
import com.example.project.Form.ProductsForm;
import com.example.project.Repository.ProductsRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductsService {
    
    @Autowired
    ProductsRepository productsRepository;
    
    public List<Products> getAll() {
        
        return productsRepository.findAll();
    }
    
    public Products create(ProductsForm form) {
        
        Products newData = new Products();
        newData.setName(form.getName());
        newData.setStock(form.getStock());
        newData.setPrice(form.getPrice());
        newData.setDisplay(true);
        
        return productsRepository.save(newData);
    }
    
    public Products put(Products product) {
        Optional<Products> target = productsRepository.findById(product.getId());
        if(target.isEmpty()) {
            return null;
        } else {
            productsRepository.save(product);
        }
        return product;
    }
    
    public Boolean delete(Integer id) {
        
        Optional<Products> target = productsRepository.findById(id);
        if(target.isEmpty()) {
            return false;
        } else {
            productsRepository.deleteById(id);
        }
        return true;
    }
    
    
    
}
