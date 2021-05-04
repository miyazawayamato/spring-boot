package com.example.project.Controller;

import java.util.List;

import com.example.project.Entity.Customers;
import com.example.project.Form.CustomersForm;
import com.example.project.Service.CustomersService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/api")
public class CustomersController {
    
    @Autowired
    CustomersService customers;
    
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customers> customersGet() {
        List<Customers> list = customers.getAll();
        return list;
    }
    
    @RequestMapping(value = "/customer/create", method = RequestMethod.POST)
    public Customers createcustomers(@RequestBody CustomersForm form) {
        
        Customers pro = customers.create(form);
        return pro;
    }
    
    @RequestMapping(value = "/customer/put", method = RequestMethod.PUT)
    public Customers putcustomers(@RequestBody Customers customer) {
        
        Customers pro = customers.put(customer);
        return pro;
    }
}
