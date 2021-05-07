package com.example.project.Service;

import java.util.List;
import java.util.Optional;

import com.example.project.Entity.Customers;
import com.example.project.Repository.CustomersRepository;
import com.example.project.Form.CustomersForm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomersService {
    
    @Autowired
    CustomersRepository customersRepository;
    
    public List<Customers> getAll() {
        
        return customersRepository.findAll();
    }
    
    public Customers create(CustomersForm form) {
        
        Customers newData = new Customers();
        newData.setName(form.getName());
        newData.setPhone(form.getPhone());
        newData.setAddress(form.getAddress());
        // newData.setDisplay(true);
        // return newData;
        return customersRepository.save(newData);
        
    }
    
    public Customers put(Customers customer) {
        Optional<Customers> target = customersRepository.findById(customer.getId());
        if(target.isEmpty()) {
            return null;
        } else {
            customersRepository.save(customer);
        }
        return customer;
    }
    //削除まで
    
    public Integer delete(Integer id) {
        
        Optional<Customers> target = customersRepository.findById(id);
        if(target.isEmpty()) {
            return 0;
            
        } else {
            customersRepository.deleteById(id);
        }
        return id;
    }
}
