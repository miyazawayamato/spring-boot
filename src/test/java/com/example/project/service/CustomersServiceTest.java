package com.example.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.project.Entity.Customers;
import com.example.project.Form.CustomersForm;
import com.example.project.Repository.CustomersRepository;
import com.example.project.Service.CustomersService;

public class CustomersServiceTest {
    
    @Mock
    private CustomersRepository customersRepository;

    @InjectMocks
    private CustomersService customersService;

    @BeforeEach
    public void initmocks() {
        MockitoAnnotations.openMocks(this);
    }
    
    
    @Test
    public void test_allget() {
        
        Mockito.when(customersService.getAll()).thenReturn(createCustomers());
        
        List<Customers> items = customersService.getAll();
        
        assertThat(items.get(0).getName(), is("企業A"));
        assertThat(items.get(0).getAddress(), is("B県C市D番地"));
        assertThat(items.get(0).getPhone(), is("1234567890"));
        
        assertThat(items.get(1).getName(), is("企業E"));
        assertThat(items.get(1).getAddress(), is("F県G市H番地"));
        assertThat(items.get(1).getPhone(), is("0987654321"));
        
        verify(customersRepository, times(1)).findAll();
    }
    
    @Test
    public void test_create() {
        
        CustomersForm customersForm = new CustomersForm();
        customersForm.setName("企業A");
        customersForm.setAddress("B県C市D番地");
        customersForm.setPhone("1234567890");
        
        Mockito.when(customersService.create(customersForm)).thenReturn(createCustomer());
        Customers created = customersService.create(customersForm);
        assertThat(created.getName(), is("企業A"));
        assertThat(created.getAddress(), is("B県C市D番地"));
        assertThat(created.getPhone(), is("1234567890"));
        
        created.setId(null);
        verify(customersRepository, times(1)).save(created);
    }
    
    @Test
    public void test_put() {
        
        Mockito.when(customersRepository.findById(createCustomer().getId())).thenReturn(Optional.of(createCustomer()));
        Mockito.when(customersService.put(createCustomer())).thenReturn(createCustomer());
        
        Customers putedcustomer = customersService.put(createCustomer());
        assertThat(putedcustomer.getName(), is("企業A"));
        
        verify(customersRepository, times(1)).save(createCustomer());
        verify(customersRepository, times(2)).findById(createCustomer().getId());
    }
    
    @Test
    public void test_delete() {
        
        Mockito.when(customersRepository.findById(10)).thenReturn(Optional.of(createCustomer()));
        
        Optional<Customers> customer = customersRepository.findById(10);
        // Integer num = customersRepository.delete(2);
        
        // assertThat(num, is(10));
        
        verify(customersRepository, times(1)).deleteById(customer.get().getId());
    }
    
    
    
    public Customers createCustomer() {
        
        Customers customer = new Customers();
        customer.setId(10);
        customer.setName("企業A");
        customer.setAddress("B県C市D番地");
        customer.setPhone("1234567890");
        return customer;
    }
    
    public List<Customers> createCustomers() {
        
        List<Customers> items = new ArrayList<>();
        
        Customers customer1 = new Customers();
        customer1.setId(10);
        customer1.setName("企業A");
        customer1.setAddress("B県C市D番地");
        customer1.setPhone("1234567890");
        Customers customer2 = new Customers();
        customer2.setId(20);
        customer2.setName("企業E");
        customer2.setAddress("F県G市H番地");
        customer2.setPhone("0987654321");
        
        items.add(customer1);
        items.add(customer2);
        
        return items;
    }
}
