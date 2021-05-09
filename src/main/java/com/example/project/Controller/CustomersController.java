package com.example.project.Controller;

import java.util.List;

import com.example.project.Entity.Customers;
import com.example.project.Form.CustomersForm;
import com.example.project.Service.CustomersService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/api")
public class CustomersController {
    
    @Autowired
    CustomersService customers;
    
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customers> getCustomers() {
        List<Customers> list = customers.getAll();
        return list;
    }
    
    @RequestMapping(value = "/customer/create", method = RequestMethod.POST)
    public Customers createCustomers(@RequestBody CustomersForm form) {
        
        Customers cust = customers.create(form);
        return cust;
    }
    
    @RequestMapping(value = "/customer/put", method = RequestMethod.PUT)
    public Customers putCustomers(@RequestBody Customers customer) {
        
        Customers pro = customers.put(customer);
        return pro;
    }
    
    @RequestMapping(value = "/customer/delete/{id}", method = RequestMethod.DELETE)
    public Integer deleteProduct(@PathVariable("id") Integer id) {
        
        return customers.delete(id);
    }
}
