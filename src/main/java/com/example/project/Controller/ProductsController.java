package com.example.project.Controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.project.Entity.Products;
import com.example.project.Form.ProductsForm;
import com.example.project.Service.ProductsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/api/product")
public class ProductsController {
    
    @Autowired
	ProductsService products;
    
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Products> productsGet() {
        List<Products> list = products.getAll();
        return list;
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Products createProducts(@RequestBody ProductsForm form) {
        
        Products pro = products.create(form);
        return pro;
    }
    
    @RequestMapping(value = "/put", method = RequestMethod.PUT)
    public Products putProducts(@RequestBody Products product) {
        
        Products pro = products.put(product);
        return pro;
    }
    
    //削除
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Integer deleteProduct(@PathVariable("id") Integer id) {
        
        // return id;
        return products.delete(id);
    }
    
}
