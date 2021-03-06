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
        return productsRepository.save(newData);
    }
    
    public Products put(Products product) {
        Optional<Products> target = productsRepository.findById(product.getId());
        if(target.isEmpty()) {
            return null;
        } else {
            Products newProduct = productsRepository.save(product);
            return newProduct;
        }
    }
    
    public Integer delete(Integer id) {
        
        Optional<Products> target = productsRepository.findById(id);
        if(target.isEmpty()) {
            return 0;
            // return false;
        } else {
            productsRepository.deleteById(id);
        }
        return id;
    }
    
    public void calcStock(List<ProductsForm> deliStock) {
        
        for (ProductsForm p : deliStock) {
            
            productsRepository.updateStock(p.getStock(), p.getId());
            
        }
        
    }
    
    
}
