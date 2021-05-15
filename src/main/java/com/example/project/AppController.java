package com.example.project;


import java.util.List;

import com.example.project.Entity.Customers;
import com.example.project.Entity.Products;
import com.example.project.Form.CustomersForm;
import com.example.project.Form.ProductsForm;
import com.example.project.Service.CustomersService;
import com.example.project.Service.ProductsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
	
	@Autowired
    CustomersService customer;
	
	@Autowired
	ProductsService products;
	
	@RequestMapping("/test")
	public @ResponseBody List<Products> getAll() {
		
		return products.getAll();
		
	}
	
	@ModelAttribute
    public  ProductsForm setupForm() {
        return new  ProductsForm();
    }
	
	@RequestMapping(value = "/product/create", method = RequestMethod.POST)
    public @ResponseBody Products createProducts(@Validated ProductsForm form, BindingResult bindingResult) {
        
        Products pro = products.create(form);
        return pro;
    }
	
	@RequestMapping(value = "/customer/create", method = RequestMethod.POST)
    public @ResponseBody Customers createcustomers(@Validated CustomersForm form , BindingResult bindingResult) {
        
        Customers pro = customer.create(form);
        return pro;
    }
	
	
    @RequestMapping("/hello")
	public String index() {
		return "signup";
	}
    
}
