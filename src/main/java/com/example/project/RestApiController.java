package com.example.project;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.project.Entity.Products;
import com.example.project.Form.ProductsForm;
// import com.example.project.Repository.ProductsRepository;
import com.example.project.Service.ProductsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/test")
public class RestApiController {
    
    @Autowired
	ProductsService products;
    
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Products> productsGet() {
        List<Products> list = products.getAll();
        return list;
    }
    
    @RequestMapping(value = "/product/create", method = RequestMethod.POST)
    public Products createProducts(@RequestBody ProductsForm form) {
        
        Products pro = products.create(form);
        return pro;
    }
    
    @RequestMapping(value = "/product/put", method = RequestMethod.PUT)
    public Products putProducts(@RequestBody Products product) {
        
        Products pro = products.put(product);
        return pro;
    }
    
    //削除・・・falseにするだけ
    //saveで挿入 id指定
    @RequestMapping(value = "/product/delete", method = RequestMethod.DELETE)
    public Boolean deleteProduct(@RequestBody Products product) {
        
        return products.delete(product.getId());
    }
    
    
}
